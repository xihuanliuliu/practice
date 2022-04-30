package com.jd.edi.dao;

import com.jd.edi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int addUser(User user);

    List<User> findUserList();

    User findUserByLoginName(String loginName);

    int deleteUser(Integer id);

    int deleteUserList(List<Integer> userList);

}
