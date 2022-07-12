package com.ai.aif.gitai.dao.mapper;

import com.ai.aif.gitai.dao.entity.Test;
import com.ai.aif.gitai.dao.entity.TestExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {



    int insert(Test record);

    int batchInsert(List<Test> list);


}