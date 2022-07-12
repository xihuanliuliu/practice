package com.ai.gitai.service.impl;

import com.ai.gitai.entity.Test;
import com.ai.gitai.mapper.TestMapper;
import com.ai.gitai.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private static Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    private TestMapper testMapper;

    @Override
    public void insert(Test test) {
        log.info("insert: {}", test.toString());
        testMapper.insert(test);
    }

    @Override
    public void batchInsert(List<Test> tests) {
        testMapper.batchInsert(tests);
    }
}
