package com.ai.aif.gitai;

import com.ai.aif.gitai.dao.Constants;
import com.ai.aif.gitai.dao.entity.SystemConfig;
import com.ai.aif.gitai.service.SystemConfigService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
public class TestSystem {


    @Autowired
    private SystemConfigService systemConfigService;


    private void useOldHostAddress(Long ...params) {
        Long repositoryId = null;
        Long projectId = null;
        if (params == null) {
            System.out.println("-------");
            return;
        }
        if (params.length > 0) {
            repositoryId = params[0];
        }
        if (params.length > 1){
            projectId = params[1];
        }
        System.out.println("re: " + repositoryId);
        System.out.println("pro: " + projectId);

    }
    @Test
    public void testGetList() {
        List<SystemConfig> systemConfigs = systemConfigService.selectByExample();

//        // 思想，有多个host， h1,h2,h3.应该怎么设置呢
//        String gitlabHost = "http://10.1.245.125:8190";
//        String privateToken = "";
//        String isLastHost = "";
//        String url = "http://10.1.245.125:8190/test/test-app-repository.git";
//        String newHost = "";
//        for (SystemConfig systemConfig : systemConfigs) {
//            if (url.startsWith(gitlabHost)) {
//                System.out.println("TestSystem.testGetList");
//            }
//
//        }

        useOldHostAddress(3L, 2L);
        System.out.println("----------------");
        useOldHostAddress(3L, null);
        System.out.println("----------------");
        useOldHostAddress(null, 2L);
        System.out.println("----------------");
        useOldHostAddress(null);
    }
}
