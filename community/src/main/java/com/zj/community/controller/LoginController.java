package com.zj.community.controller;

import com.zj.community.entity.User;
import com.zj.community.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RegisterService registerService;

    @GetMapping(value = "/register")
    public String register(Model model, User user) {
        // 注册账号
        Map<String, Object> map = registerService.registerUser(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        }
        model.addAttribute("usernameMsg", map.get("error"));
        model.addAttribute("passwordMsg", map.get("error"));
        model.addAttribute("emailMsg", map.get("error"));
        return "/site/register";
    }

}
