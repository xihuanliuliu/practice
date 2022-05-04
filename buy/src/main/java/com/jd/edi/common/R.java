package com.jd.edi.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> {

    private Integer code;
    private T data;
    private String msg;

    // 动态数据
    private Map map = new HashMap();

    public static <T> R<T> success (T data){
        R<T> r = new R<>();
        r.data = data;
        r.code = 1;
        r.msg = "success";
        return r;
    }

    public static <T> R<T> error (String msg){
        R r = new R();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    public R<T> add(String key, String msg){
        this.map.put(key, msg);
        return this;
    }
}
