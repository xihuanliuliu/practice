package com.jd.module.service;

import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Override
    public String testModule() {
        return "测试springboot的扫描范围";
    }
}
