package com.jd.edi.test;

import com.jd.edi.mapper.TestMapper;
import com.jd.edi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.Date;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private TestService testService;
    @Autowired
    private TestMapper testMapper;

    @Test
    public void insertTest() {
        com.jd.edi.entity.Test test = new com.jd.edi.entity.Test();
        test.setActive("ac");
        test.setBuName("bu");
        test.setProjId(1L);
        test.setCreateTime(new Date());
        testService.insert(test);
    }
}
