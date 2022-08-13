package com.zj.community.service;

import com.zj.community.entity.User;

public interface UserService {

    User queryUserById(Long userId);

    User queryUserByUserName(String userName);

    User queryUserByEmail(String email);

    int insertUser(User user);
}
