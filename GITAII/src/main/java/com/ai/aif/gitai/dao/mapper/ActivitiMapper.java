package com.ai.aif.gitai.dao.mapper;

import com.ai.aif.gitai.dao.entity.ActivitiModel;
import com.ai.aif.gitai.dao.entity.ExceptionJobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActivitiMapper {

    List<Map<String,Object>> queryActivitiList(Map<String, Object> map);

    List<ActivitiModel> queryModelList(@Param("modelName") String modelName);

    List<ExceptionJobEntity> getExceptionJob(@Param("processId") String processId);
}
