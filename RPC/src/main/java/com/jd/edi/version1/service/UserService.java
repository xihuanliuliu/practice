package com.jd.edi.version0.service;

import com.jd.edi.version0.common.User;

public interface UserService {

    void addUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);

}
