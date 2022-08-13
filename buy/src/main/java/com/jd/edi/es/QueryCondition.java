package com.jd.edi.es;

import lombok.Data;

@Data
public class QueryCondition {

    private String pageNum;
    private String pageSize;
    private String sort;
    private String filed;

    private String boolValue;
    private String boolKey;

    private String filterName;
    private String filterKey;


}
