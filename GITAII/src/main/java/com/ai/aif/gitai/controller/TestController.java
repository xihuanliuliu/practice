package com.ai.aif.gitai.controller;

import com.ai.aif.gitai.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/test")
    public String getApplyTypeList(){
        return "ajaxResponse";
    }
}
