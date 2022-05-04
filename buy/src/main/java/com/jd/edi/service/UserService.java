package com.jd.edi.service;


import com.jd.edi.entity.User;

public interface UserService {

    User getUserByPhone(String phone);

    void saveUser(User user);

}
