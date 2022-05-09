package com.jd.edi.version1.service;

import com.jd.edi.version1.common.User;

public interface UserService {

    void addUser(User user);

    User getUser(Integer id);

    void deleteUser(Integer id);

}
