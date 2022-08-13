package com.zj.community.controller;

import com.zj.community.common.CommonResponse;
import com.zj.community.common.ResponseCodeEnum;
import com.zj.community.entity.User;
import com.zj.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public CommonResponse<Object> test(Long userId) {
        System.out.println("--------");
        User user = userService.queryUserById(userId);
        System.out.println(user.toString());
        return CommonResponse.success(user);
    }

}
