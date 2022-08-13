package com.zj.community.common;

public class CommonResponse<T> {

    private String code;

    private String msg;

    private T data;

    public CommonResponse(String code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public CommonResponse() {}

    public CommonResponse(ResponseCodeEnum responseCodeEnum) {
        if (responseCodeEnum != null) {
            this.code = responseCodeEnum.getCode();
            this.msg = responseCodeEnum.getMsg();
        }
    }

    public CommonResponse(T data, ResponseCodeEnum responseCodeEnum) {
        this(responseCodeEnum);
        this.data = data;
    }

    public CommonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse(data, ResponseCodeEnum.SUCCESS);
    }

    public static <T> CommonResponse<T> fail() {
        return new CommonResponse<T>(ResponseCodeEnum.FAIL);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(ResponseCodeEnum.SUCCESS);
    }

    public static <T> CommonResponse<T> success(String code, String msg, T data) {
        return new CommonResponse<>(code, msg, data);
    }

    public static <T> CommonResponse<T> fail(String code, String msg) {
        return new CommonResponse<>(code, msg);
    }

    public static <T> CommonResponse<T> fail(String code, String msg, T data) {
        return new CommonResponse<>(code, msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
