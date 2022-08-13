package com.jd.edi.exception;

public class EsException extends RuntimeException {

    private String msg;

    private String code;

    public EsException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public EsException(String msg) {
        super(msg);
    }


}
