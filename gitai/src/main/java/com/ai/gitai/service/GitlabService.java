package com.ai.gitai.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public interface GitlabService extends ApplicationListener<ContextRefreshedEvent> {

    void getGitlab(Integer id);
}
