package com.jd.edi.controller;

import com.jd.edi.common.R;
import com.jd.edi.entity.User;
import com.jd.edi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();

        // 正常逻辑是去调用第三方

        // 自己做就生成随机码
        if (StringUtils.isNotEmpty(phone)) {
            //String code = ValidateCodeUtils.generateValidateCode(4).toString();
            String code = "1234";
            session.setAttribute(phone, code);
        }
        return R.success("发送消息成功");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        // 1.获取到phone
        String phone = map.get("phone").toString();
        // 2.获取到code
        String code = map.get("code").toString();
        // 3.从session中获取 code
        Object sessionCode = session.getAttribute(phone);
        // 4.匹配code
        if (sessionCode != null && sessionCode.equals(code)) {
            User user = userService.getUserByPhone(phone);
            // 5.如果是新号，那么自动给他注册
            if (user == null) {
                user = new User();
                user.setStatus(1);
                user.setPhone(phone);
                userService.saveUser(user);
            }
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("登录失败");
    }

}
