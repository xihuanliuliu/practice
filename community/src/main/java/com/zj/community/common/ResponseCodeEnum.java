package com.zj.community.common;


public enum ResponseCodeEnum {

    FAIL("400", "fail"),
    SUCCESS("200", "success");

    private String msg;
    private String code;

    private ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }



}
