package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.entity.Setmeal;
import com.jd.edi.entity.SetmealDish;
import com.jd.edi.exception.CategoryException;
import com.jd.edi.mapper.SetmealMapper;
import com.jd.edi.service.SetmealDishService;
import com.jd.edi.service.SetmealService;
import com.jd.edi.vo.SetmealDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Resource
    private SetmealMapper setmealMapper;

    @Resource
    private SetmealDishService setmealDishService;

    @Override
    public Integer countSetmealByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getCategoryId, categoryId);
        return setmealMapper.selectCount(queryWrapper);
    }

    @Override
    public List<Setmeal> getSetmealList(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null,Setmeal::getStatus,setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        return setmealMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSetmeal(SetmealDto setmealDto) {
        // 1.插入setmeal
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDto, setmeal);
        log.info("setmeal: {}", setmeal);
        setmealMapper.insert(setmeal);

        // 2. 插入到setmealDish中
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            item.setCreateTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateUser(AuthContext.currentUser());
            item.setUpdateUser(AuthContext.currentUser());
            // BeanUtils.copyProperties(setmealDto, setmeal); 使用了这个之后，就不再使用setmealDto
            item.setSetmealId(setmeal.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveSetmealDish(setmealDishes);
    }

    @Override
    public Page<Setmeal> getSetmealPage(Integer page, Integer pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        return setmealMapper.selectPage(setmealPage, queryWrapper);
    }

    @Override
    public Setmeal getSetmeal(Long id) {
        return setmealMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSetmeal(SetmealDto setmealDto) {
        // 1.更新setmeal
        setmealMapper.updateById(setmealDto);

        // 2.删除setmealDish
        setmealDishService.deleteSetmealDish(Arrays.asList(setmealDto.getId()));

        // 3.插入setmealDish
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item)->{
            item.setCreateTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateUser(AuthContext.currentUser());
            item.setUpdateUser(AuthContext.currentUser());
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveSetmealDish(setmealDishes);

    }

    @Transactional
    @Override
    public void deleteSetmeal(List<Long> ids) {
        // 1. 是否能删除
        Integer count = countSetmealStatus(ids);
        if (count > 0) {
            throw new CategoryException("在售套餐，不能删除");
        }
        // 1.删除setmeal
        setmealMapper.deleteBatchIds(ids);

        // 2.删除setmealDish
        setmealDishService.deleteSetmealDish(ids);
    }

    @Override
    public Integer countSetmealStatus(List<Long> ids) {
        // select count(*) from setmeal in (id1,id2) and status = 1
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        return setmealMapper.selectCount(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSetmealStatus(List<Long> ids, Integer status) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        setmealMapper.update(setmeal, queryWrapper);
    }
}
