package com.jd.edi.mapper;



import com.jd.edi.entity.Test;
import com.jd.edi.entity.TestExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestMapper {
    long countByExample(TestExample example);

    int deleteByExample(TestExample example);

    int insert(Test record);

    int batchInsert(List<Test> list);

    int insertSelective(Test record);

    List<Test> selectByExample(TestExample example);

    List<Test> selectErrorDatas(Test test);
    
    int updateByExampleSelective(@Param("record") Test record, @Param("example") TestExample example);

    int updateByExample(@Param("record") Test record, @Param("example") TestExample example);
}