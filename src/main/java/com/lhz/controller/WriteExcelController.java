package com.lhz.controller;

import com.lhz.config.ResponseObject;
import com.lhz.service.WriteExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LiHuaZhi
 * @Date: 2020/5/1 12:21
 * @Description:
 **/
@RestController
@Api(tags = "导出Excel")
@RequestMapping("/write")
public class WriteExcelController {

    @Resource
    private WriteExcelService writeExcelService;

    @GetMapping("/user")
    @ApiOperation(value = "导出用户excel", notes = "导出用户excel")
    public Object writePerson() {
        writeExcelService.writePersonExcel();
        return ResponseObject.success();
    }

    @GetMapping("/user/down")
    @ApiOperation(value = "web下载excel", notes = "web下载excel")
    public Object writePerson(HttpServletResponse response) throws Exception {
        writeExcelService.webWritePersonExcel(response);
        return ResponseObject.success();
    }
}
