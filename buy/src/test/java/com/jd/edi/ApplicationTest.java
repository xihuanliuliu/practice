package com.jd.edi;

import com.jd.edi.entity.User;
import com.jd.module.service.ModuleService;
import com.jd.edi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ModuleService moduleService;
    @Test
    public void testUserService() {
        User userById = userService.getUserById(1521669357632602113L);
        // assert condition:expr;
        System.out.println("user info : " + userById.toString());
         assert userById.getId() != null : "不存在此用户";
    }

    @Test
    public void testModule() {
        String s = moduleService.testModule();
        System.out.println("module service: " + s);
    }
}
