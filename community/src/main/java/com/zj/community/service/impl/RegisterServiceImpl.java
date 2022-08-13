package com.zj.community.service.impl;

import com.zj.community.configuration.MailClient;
import com.zj.community.entity.User;
import com.zj.community.service.RegisterService;
import com.zj.community.service.UserService;
import com.zj.community.utils.CommunityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserService userService;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public Map<String, Object> registerUser(User user) {
        Map<String, Object> map = new HashMap<>();
        // 首先判断输入的参数
        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword(), user.getEmail())) {
            map.put("error", "用户名密码邮箱为空");
        }
        // 判断是否已经存在账号
        User userByUserName = userService.queryUserByUserName(user.getUsername());
        if (userByUserName != null || StringUtils.isNotEmpty(userByUserName.getUsername())) {
            map.put("username", "存在");
        }
        User userByEmail = userService.queryUserByEmail(user.getEmail());
        if (userByEmail != null || StringUtils.isNotEmpty(userByEmail.getEmail())) {
            map.put("email", "存在");
        }
        // 往表里插数据
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());
        userService.insertUser(user);

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }
}
