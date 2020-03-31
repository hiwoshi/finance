package com.finance.common;

import com.alibaba.fastjson.JSONObject;

/**
 * @author : shenhao
 * @date : 2019/10/8 13:37
 */
public class R {

    private String result;
    private Object data;
    private String message;

    public static R success (Object data){
        return new R("success", data, "");
    }

    public static R error (String message){
        return new R("error", null, message);
    }

    public R() {
    }

    public R(String result) {
        this.result = result;
    }

    public R(String result, Object data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
