package com.jd.edi.exception;


import com.jd.edi.vo.ResponseEnum;

public class RedisException extends RuntimeException {

    private String code;

    private String msg;

    private ResponseEnum responseEnum;
    public RedisException() {}

    public RedisException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public RedisException(String msg) {
        super(msg);
    }

    public RedisException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;

    }
}
