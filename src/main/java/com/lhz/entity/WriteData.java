package com.lhz.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentLoopMerge;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.lhz.converter.CustomWriteNameConverter;
import lombok.Data;

import java.util.Date;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/11 21:18
 * @Description:
 **/
@Data
//设置宽、高
@HeadRowHeight(30) //表头行高
@ContentRowHeight(20) //内容行高
//列宽，可以单独写到某个注解上，进行单独设置
@ColumnWidth(25)
//更多设置注解可以参考文档
public class WriteData {
    //注解会自动写入表头，并且加上样式
    //加上 converter  进入到converter 类中实现自定义的内
    @ExcelProperty(value = "名称",converter = CustomWriteNameConverter.class)
    // 这一列 每隔2行 合并单元格
    @ContentLoopMerge(eachRow = 2)
    private String name;
    //可以采用传入多个参数，达到复杂表头合并，会自动匹配名称进行层级的合并操作
    @ExcelProperty({"一级","合并","时间"})
    @DateTimeFormat("yyyy年MM月dd日")//时间格式化
    private Date date;

    @ExcelProperty({"一级","合并","年龄"})
    private Integer age;

    @ExcelProperty({"一级","合并","性别"})
    private Integer sex;

    @NumberFormat("#.##") //数字格式化处理
    @ExcelProperty({"一级","绩效"})
    @ColumnWidth(20)
    private Double per;
}
