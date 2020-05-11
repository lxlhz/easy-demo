package com.lhz.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.lhz.entity.WriteData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 15:24
 * @Description: 导出excl的Service类
 **/
@Service
@Slf4j
public class WriteExcelService {

    //模拟获取数据
    private List<WriteData> getWriteData() {
        List<WriteData> list = new ArrayList<WriteData>();
        for (int i = 0; i < 10; i++) {
            WriteData data = new WriteData();
            data.setName("名称 " + i);
            data.setDate(new Date());
            data.setAge(20 + i);
            data.setSex(i % 2);
            data.setPer(1.235 + 1);
            list.add(data);
        }
        return list;
    }


    public void writePersonExcel() {
        //获取数据
        List<WriteData> writeData = getWriteData();
        // 写法1
        String fileName = "C:\\Users\\LiHuaZhi\\Desktop" + File.separator + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, WriteData.class).sheet("模板").doWrite(writeData);

        /*
        // 写法2
        // 这里 需要指定写用哪个class去写,并且写入sheet名称
        ExcelWriter excelWriter = EasyExcel.write(fileName, WriteData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        //如何sheet名称相同可以直接遍历写入
        excelWriter.write(writeData, writeSheet);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
        */
    }

    public void webWritePersonExcel(HttpServletResponse response) throws Exception {
        //获取数据
        List<WriteData> writeData = getWriteData();

        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), WriteData.class).sheet("模板").doWrite(writeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
