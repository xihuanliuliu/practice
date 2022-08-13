package com.zj.community.service.impl;

import com.zj.community.entity.User;
import com.zj.community.mapper.UserMapper;
import com.zj.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User queryUserByUserName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    public User queryUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
}
