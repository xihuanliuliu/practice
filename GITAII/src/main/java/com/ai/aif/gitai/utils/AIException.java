package com.ai.aif.gitai.utils;

public class AIException extends Exception {
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public AIException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public AIException(String errorString) {
        this(ErrorCode.DEF_ERROR.getCode(), errorString);
    }

    public AIException(String format, Object ...args) {
        this(ErrorCode.DEF_ERROR.getCode(), format, args);
    }

    public AIException(String errorCode, String format, Object ...args) {
        this(errorCode, String.format(format, args));
    }

    public AIException(String errorCode, String errorString) {
        super(errorString);
        this.errorCode = errorCode;

        //log.error("ServiceException: errorCode=" + errorCode + ", errorMessage=" + errorString, this);
    }

    public AIException(String errorString, Throwable cause) {
        this(ErrorCode.DEF_ERROR.getCode(), errorString, cause);
    }

    public AIException(String errorCode, String errorString, Throwable cause) {
        super(errorString, cause);
        this.errorCode = errorCode;

        //log.error("ServiceException: errorCode=" + errorCode + ", errorMessage=" + errorString, this);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.getMessage();
    }
}
