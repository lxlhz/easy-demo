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

    @GetMapping
    @ApiOperation(value = "根据文件路径读取Excel", notes = "根据文件路径读取Excel")
    @ApiImplicitParam(name = "filePath", value = "Excel路径", paramType = "query", required = true, dataType = "String")
    public Object readPerson(@RequestParam String filePath) {
        Object obj = readExcelService.readPeronExcel(filePath);
        return ResponseObject.success(obj);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件路径读取Excel", notes = "上传文件路径读取Excel")
    @ApiImplicitParam(name = "file", value = "文件", paramType = "body", dataType = "MultipartFile", required = true)
    public Object readPersonByUpload(@RequestParam MultipartFile file) throws Exception {
        Object obj = readExcelService.readPeronExcelByUpload(file);
        return ResponseObject.success(obj);
    }

}
