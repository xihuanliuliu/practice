package com.jd.edi.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    // 总页数
    private Integer pages;

    // 总条目数
    private Long total;

    // 结果集
    private List<T> records;


}
