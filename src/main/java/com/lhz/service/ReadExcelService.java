package com.lhz.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lhz.entity.PersonData;
import com.lhz.exception.ServiceException;
import com.lhz.listener.PersonDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 15:24
 * @Description: 读取excel的Service类
 **/
@Service
@Slf4j
public class ReadExcelService {
    /**
     * 每隔3000条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;

    @Resource
    private PersonDataListener personDataListener;

    //读取用户选择的excel文件
    public Object readPeronExcel(String filePath) {

        //设置headRowNumber表示从第几行开始读取，下标从0开始
        // 写法1：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, PersonData.class, personDataListener).sheet().headRowNumber(2).doRead();

        /*
         // 写法2：
         ExcelReader excelReader = EasyExcel.read(filePath, PersonData.class, new PersonDataListener()).build();
         //指定读取的Sheet
         ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(2).build();
         excelReader.read(readSheet);
         // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
         excelReader.finish();
         */

        return "成功";
    }


    public Object readPeronExcelByUpload(MultipartFile file) throws Exception {
        //设置headRowNumber表示从第几行开始读取，下标从0开始
        EasyExcel.read(file.getInputStream(), PersonData.class, personDataListener).sheet().headRowNumber(2).doRead();

        return "成功";
    }

    //对导入数据进行业务判断
    public void checkPersonExcel(PersonData personData, Integer rowIndex) {
        if (StringUtils.isEmpty(personData.getName()) || StringUtils.isEmpty(personData.getDept()) || StringUtils.isEmpty(personData.getDate())) {
            throw new ServiceException(500, "第" + rowIndex + "行，数据不能为空");
        }
    }

    //保存有效的导入数据
    public void savePersonExcel(List<PersonData> list) {
        //对数据进行一些业务处理,比如将月薪大于5000的数据，分出来
        List<PersonData> collect = list.stream().filter(li -> li.getMonth() >= 5000).collect(Collectors.toList());
        log.info("月薪达标按数量：" + collect.size());

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            //插入数据库
            log.info("插入数据库：" + list.size());
        }
    }
}
