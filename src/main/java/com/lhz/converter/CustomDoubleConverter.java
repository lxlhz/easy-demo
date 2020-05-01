package com.lhz.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.DecimalFormat;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 16:08
 * @Description:
 **/
public class CustomDoubleConverter implements Converter<Double> {
    @Override
    public Class supportJavaTypeKey() {
        return Double.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    /**
     * 这里读的时候会调用
     *
     * @param cellData            NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public Double convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        CellDataTypeEnum type = cellData.getType();
        double newValue = 0.00d;
        if (type == CellDataTypeEnum.STRING) {
            //如果为文本则转换为为Double
            newValue = Double.valueOf(cellData.getStringValue());
        } else {
            newValue = Double.valueOf(cellData.getNumberValue().toString());
        }
        return newValue;
    }

    /**
     * 这里是写的时候会调用
     *
     * @param value               NotNull
     * @param contentProperty     Nullable
     * @param globalConfiguration NotNull
     * @return
     */
    @Override
    public CellData convertToExcelData(Double value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        return new CellData(value);
    }
}