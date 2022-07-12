package com.ai.aif.gitai.service.impl;


import com.ai.aif.gitai.dao.entity.SystemConfig;
import com.ai.aif.gitai.dao.entity.SystemConfigExample;
import com.ai.aif.gitai.dao.entity.SystemConfigExample.Criteria;
import com.ai.aif.gitai.dao.mapper.SystemConfigMapper;
import com.ai.aif.gitai.service.SystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {
	
	@Resource
	private SystemConfigMapper systemConfigMapper;
	
	public List<SystemConfig> selectByExample() {
        
        SystemConfigExample example = new SystemConfigExample();
            
        return systemConfigMapper.selectByExample(example);
    
        
    }

    @Override
	public List<SystemConfig> selectByConfigKey(String configKey) {
		
	    SystemConfigExample example = new SystemConfigExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andConfigKeyEqualTo(configKey);
	    
	    return systemConfigMapper.selectByExample(example);
	}
	
	
	

}
