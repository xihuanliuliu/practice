package com.ai.aif.gitai.service.impl;


import com.ai.aif.gitai.dao.Constants;
import com.ai.aif.gitai.dao.entity.*;

import com.ai.aif.gitai.service.GitlabService;
import com.ai.aif.gitai.service.SystemConfigService;
import com.ai.aif.gitai.utils.PropertiesConfigurationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class GitlabServiceImpl implements GitlabService {

    private static final int MASTER_ACCESS = 40;
    private static final int DEVELOPER_ACCESS = 30;
    private static final int REPORTER_ACCESS = 20;
    private static final String NO_ACCESS = "当前登录用户权限不足，请联系项目管理员！！";
    private static final String PROTECTED_BRANCH = "当前分支受保护，不可删除！";
    private static final Log LOGGER = LogFactory.getLog(GitlabServiceImpl.class);

    private String gitlabHost;

    private String privateToken;

    @Autowired
    private SystemConfigService systemConfigService;



    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("启动执行onApplicationEvent");
        String property = PropertiesConfigurationUtil.getProperty("gitai.es");
        System.out.println("value: " + property);
        System.out.println("-------------------");
        String property2 = PropertiesConfigurationUtil.getProperty("spring.datasource.password");
        System.out.println("value2: " + property2);
    }

}
