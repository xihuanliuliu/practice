package com.jd.edi.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@Slf4j
@ConfigurationProperties(prefix = "auth")
public class AuthPropertyLoader implements InitializingBean {

    // auth.config
    private Map<String, String> config;

    public Map<String, String> getConfig() {
        return config;
    }
    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        AuthContext.setAuthConfig(config);
        log.info("add auth config :{}", config);
    }
}
