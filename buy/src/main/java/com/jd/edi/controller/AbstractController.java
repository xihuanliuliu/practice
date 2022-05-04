package com.jd.edi.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public abstract class AbstractController {

    public String getMd5Data (String data) {
        return "";
    }

    public Long getCurrentUserId(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("employee");
        log.info("userId: {}", userId);
        return userId;


    }

}
