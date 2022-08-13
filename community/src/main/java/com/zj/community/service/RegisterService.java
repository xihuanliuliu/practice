package com.zj.community.service;

import com.zj.community.entity.User;

import java.util.Map;

public interface RegisterService {

    Map<String, Object> registerUser(User user);

}
