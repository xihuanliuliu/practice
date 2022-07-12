package com.ai.aif.gitai.service;

import com.ai.aif.gitai.dao.entity.SystemConfig;

import java.util.List;

public interface SystemConfigService {

    /**
     * 返回所有服务表信息
     */
    List<SystemConfig> selectByExample();

    /**
     * 根据key获取配置表的信息
     * @param configKey
     * @return 
     */
    List<SystemConfig> selectByConfigKey(String configKey);
}
