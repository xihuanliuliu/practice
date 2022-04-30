package com.jd.edi.entity;

import lombok.Data;

@Data
public class User {

    // id
    private Integer id;
    private String userName;
    private String loginName;
    private String pwd;
    private String sex;

}
