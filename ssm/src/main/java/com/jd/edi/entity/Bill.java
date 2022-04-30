package com.jd.edi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Bill {
    // id
    private Integer id;
    // title
    private String title;
    // type id
    private Integer typeId;
    // date yyyy-MM-dd HH:mm:ss
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat
    private Date billTime;
    // price
    private Double price;
    // remake
    private String remake;

    // typeName 账单类型
    private String typeName;


}
