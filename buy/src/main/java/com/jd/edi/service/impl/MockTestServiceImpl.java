package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.entity.Dish;
import com.jd.edi.entity.User;
import com.jd.edi.exception.RedisException;
import com.jd.edi.mapper.DishMapper;
import com.jd.edi.mapper.UserMapper;
import com.jd.edi.service.DishService;
import com.jd.edi.service.MockTestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MockTestServiceImpl implements MockTestService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DishService dishService;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public int deleteUser(Long id) {
        if (id == null) {
            return 0;
        }
        return userMapper.deleteById(id);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> queryUsers(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public User selectUserByPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone, phone);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User selectUserAndDish(Long userId, Long dishId, String param) {
        if (StringUtils.isAnyBlank(String.valueOf(userId), String.valueOf(dishId), param)) {
            throw new RedisException("参数为空");
        }
        Dish dishById = dishService.getDishById(dishId);
        if (dishById == null) {
            throw new RedisException("dish为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone, param);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new RedisException("user为空");
        }
        if (!user.getPhone().equals(dishById.getName())) {
            throw new RedisException("参数不等");
        }

        user.setAvatar(dishById.getName());

        return user;
    }
}
