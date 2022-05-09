package com.jd.edi.version1.provider;

import com.jd.edi.version1.common.User;
import com.jd.edi.version1.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {
        System.out.println("user service add user: " + user.toString());
    }

    @Override
    public User getUser(Integer id) {
        User user = User.builder().name("张晶")
                .id(id)
                .sex("男").build();
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        System.out.println("user service delete user: " + id);
    }
}
