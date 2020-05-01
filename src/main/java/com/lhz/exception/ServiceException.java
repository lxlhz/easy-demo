package com.lhz.exception;

/**
 * Created by Vi
 * Date on 2019/4/17 11:07
 */
public class ServiceException extends RuntimeException {
    private Integer code;
    private String message;

    public ServiceException(int code, String state) {
        this.code = code;
        this.message = state;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
