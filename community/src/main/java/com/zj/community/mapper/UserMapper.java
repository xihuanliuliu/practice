package com.zj.community.mapper;

import com.zj.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(Long id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);
//
//    int updateStatus(Long id, Long status);
//
//    int updateHeader(Long id, String headerUrl);
//
//    int updatePassword(Long id, String password);
}
