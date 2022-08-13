package com.jd.edi.spring.exception;

public class RedisException extends RuntimeException {

    private String code;

    private String message;

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message,  String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RedisException(String message,  String code) {
        super(message);
        this.code = code;
    }

    public RedisException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public RedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
