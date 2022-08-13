package com.zj.community.controller;

import com.zj.community.entity.DiscussPost;
import com.zj.community.entity.User;
import com.zj.community.service.DiscussPostService;
import com.zj.community.service.UserService;
import com.zj.community.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String getIndex(Model model, Page page) {
        // 方法调用钱,SpringMVC会自动实例化Model和Page,并将Page注入Model.
        // 所以,在thymeleaf中可以直接访问Page对象中的数据.
        page.setRows(discussPostService.selectDiscussPostRows(0L));
        page.setPath("/index");


        List<DiscussPost> list = discussPostService.queryDiscussPosts(0L, page);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                System.out.println("post: " + post.toString());
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.queryUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "/index";



    }


}
