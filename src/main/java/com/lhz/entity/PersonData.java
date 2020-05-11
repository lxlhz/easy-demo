package com.lhz.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.lhz.converter.CustomDoubleConverter;
import lombok.Data;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 15:13
 * @Description: 人员实体类
 **/
@Data
//注意属性的顺序对应excel中各个列解析的顺序，为了处理单元格为空的情况莫须有写一个Converter类，对空进行判断，并且返回默认值
public class PersonData {
    //名称
    private String name;
    //入职时间,日期格式化操作(不用关系excel中的日期为文本还是自定义日期格式)
    @DateTimeFormat("yyyy-MM-dd")//只会转换excel中的时间格式，如果excel本来就是String类型，需要写一个转换器进行统一的转换
    private String date;
    //部门
    private String dept;

    //年薪，对excel里面的数据进行统一的转换，当解析到年薪单元格时，会CustomDoubleConverter中进行格式转换操作
    @ExcelProperty(converter = CustomDoubleConverter.class)
    private Double year = 0.00;
    //月薪，对excel里面的数据进行统一的转换
    @ExcelProperty(converter = CustomDoubleConverter.class)
    private Double month = 0.00;
}
