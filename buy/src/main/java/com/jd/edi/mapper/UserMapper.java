package com.jd.edi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.edi.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
