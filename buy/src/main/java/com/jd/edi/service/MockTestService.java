package com.jd.edi.service;

import com.jd.edi.entity.User;

import java.util.List;

public interface MockTestService {

    void addUser(User user);

    int deleteUser(Long id);

    User selectUserById(Long id);

    List<User> queryUsers(User user);

    User selectUserByPhone(String phone);

    User selectUserAndDish(Long userId, Long dishId, String param);

}
