package com.jd.edi.demo.generic;

public class ResponseDemo <T>{
    private Integer code;
    private String message;
    // 不确定data是什么类型
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public  T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
