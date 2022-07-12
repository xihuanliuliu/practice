package com.ai.gitai.service.impl;


import com.ai.gitai.service.GitlabService;

import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;
import java.util.Map;

public class GitlabServiceImpl implements GitlabService {

    private String host;
    private String token;


    public void getGitlab(Integer id) {

    }

    // 监听事件，当注入bean时调用这个方法，可以去初始化 host， token参数

    // 比如监听ContextRefreshedEvent事件，当所有的bean都初始化完成并被成功装载后会触发该事件，
    // 实现ApplicationListener<ContextRefreshedEvent>接口可以收到监听动作，然后可以写自己的逻辑。
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent().getParent() == null) {
//            log.info("初始化系统参数 host, token");
            // 从其他地方读取
            host = "host";
            token = "token";
        }
    }
}
