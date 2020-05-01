package com.lhz.entity;

import lombok.Data;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 15:13
 * @Description: 设备实体类
 **/
@Data
public class DeviceData {
    //名称
    private String name;

    //设备编码
    private String code;

    //设备价格
    private String price;
}
