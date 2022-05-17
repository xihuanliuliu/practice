package com.jd.edi.version3.service;

import com.jd.edi.version3.common.User;

public interface UserService {

    void addUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);

}
