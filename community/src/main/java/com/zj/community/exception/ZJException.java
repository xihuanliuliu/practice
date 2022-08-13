package com.zj.community.exception;

public class ZJException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ZJException() {}

    public ZJException(String msg) {
        super(msg);
    }

    public ZJException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
