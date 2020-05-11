package com.lhz.controller;

import com.lhz.config.ResponseObject;
import com.lhz.service.ReadExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 12:21
 * @Description:
 **/
@RestController
@Api(tags = "读取Excel")
@RequestMapping("/read")
public class ReadExcelController {

    @Resource
    private ReadExcelService readExcelService;

    @GetMapping("/user")
    @ApiOperation(value = "录入用户文件路径", notes = "录入用户文件路径")
    @ApiImplicitParam(name = "filePath", value = "Excel路径", paramType = "query", required = true, dataType = "String")
    public Object readPerson(@RequestParam String filePath) {
        readExcelService.readPeronExcel(filePath);
        return ResponseObject.success();
    }

    @GetMapping("/device")
    @ApiOperation(value = "录入设备文件路径", notes = "录入设备文件路径")
    @ApiImplicitParam(name = "filePath", value = "Excel路径", paramType = "query", required = true, dataType = "String")
    public Object readDevice(@RequestParam String filePath) {
        readExcelService.readDeviceExcel(filePath);
        return ResponseObject.success();
    }

    @PostMapping("/user/upload")
    @ApiOperation(value = "上传用户文件", notes = "上传用户文件")
    @ApiImplicitParam(name = "file", value = "文件", paramType = "body", dataType = "MultipartFile", required = true)
    public Object readPersonByUpload(@RequestParam MultipartFile file) throws Exception {
        readExcelService.readPeronExcelByUpload(file);
        return ResponseObject.success();
    }

}
