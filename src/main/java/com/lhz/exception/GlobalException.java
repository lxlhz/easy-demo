package com.lhz.exception;


import com.lhz.config.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * 全局异常处理
 * Created by Vi
 * Date on 2019/4/17 11:07
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 服务异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResponseObject handlerException(Exception e) {
        e.printStackTrace();
        return ResponseObject.fail(e.getMessage());
    }


    //自定义异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public ResponseObject handleException(ServiceException e) {
        e.printStackTrace();
        return ResponseObject.fail(e.getMessage());
    }

}
