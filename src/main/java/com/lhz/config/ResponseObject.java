package com.lhz.config;


/**
 * Created by Vi
 * Date on 2019/4/15 15:16
 */
public class ResponseObject {
    private String msg;
    private int code;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static ResponseObject success() {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setData(true);
        ResponseObject.setCode(200);
        ResponseObject.setMsg("操作成功");
        return ResponseObject;
    }


    public static ResponseObject success(Object object) {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setData(object);
        ResponseObject.setCode(200);
        ResponseObject.setMsg("操作成功");
        return ResponseObject;
    }

    public static ResponseObject success(String msg) {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setData(true);
        ResponseObject.setCode(200);
        ResponseObject.setMsg(msg);
        return ResponseObject;
    }

    public static ResponseObject fail() {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setData(false);
        ResponseObject.setCode(400);
        ResponseObject.setMsg("操作失败");
        return ResponseObject;
    }


    public static ResponseObject fail(String msg) {
        ResponseObject ResponseObject = new ResponseObject();
        ResponseObject.setData(false);
        ResponseObject.setCode(400);
        ResponseObject.setMsg(msg);
        return ResponseObject;
    }

}
