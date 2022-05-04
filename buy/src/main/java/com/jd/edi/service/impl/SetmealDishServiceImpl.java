package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.entity.SetmealDish;
import com.jd.edi.mapper.SetmealDishMapper;
import com.jd.edi.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class SetmealDishServiceImpl implements SetmealDishService {

    @Resource
    private SetmealDishMapper setmealDishMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveSetmealDish(List<SetmealDish> setmealDishList) {
        for (SetmealDish setmealDish : setmealDishList) {
            log.info("setmealDish : {}", setmealDish);
            setmealDishMapper.insert(setmealDish);
        }
    }

    @Override
    public List<SetmealDish> getSetmealDish(Long setmealId) {
        QueryWrapper<SetmealDish> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SetmealDish::getSetmealId, setmealId);
        return setmealDishMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSetmealDish(List<Long> setmealId) {
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SetmealDish::getSetmealId, setmealId);
        setmealDishMapper.delete(queryWrapper);
    }


}
