package com.ai.gitai.service;

import com.ai.gitai.mapper.TestMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;



@SpringBootTest
public class TestServiceTest {

    @Resource
    private TestService testService;

    @Autowired
    private TestMapper testMapper;

    @Test
    public void insertTest() {
        com.ai.gitai.entity.Test test = new com.ai.gitai.entity.Test();
        test.setActive("ac");
        test.setBuName("bu");
        test.setProjId(1L);
        test.setCreateTime(new Date());
        testService.insert(test);
    }
}
