package com.jd.edi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.entity.DishFlavor;
import com.jd.edi.mapper.DishFlavorMapper;
import com.jd.edi.service.DishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DishFlavorServiceImpl implements DishFlavorService {

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatch(List<DishFlavor> flavors) {
        for (DishFlavor dishFlavor : flavors) {
            log.info("insert dishFlavor: {}", dishFlavor);
            dishFlavorMapper.insert(dishFlavor);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteBatchFlavor(List<Long> dishIds) {
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DishFlavor::getDishId, dishIds);
        dishFlavorMapper.delete(queryWrapper);
    }

    @Override
    public List<DishFlavor> getDishFlavors(Long dishId) {
        QueryWrapper<DishFlavor> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DishFlavor::getDishId, dishId);
        return dishFlavorMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDishFlavor(List<DishFlavor> flavors) {
        for (DishFlavor dishFlavor : flavors) {
            log.info("update dishFlavor: {}", dishFlavor);
            dishFlavorMapper.updateById(dishFlavor);
        }
    }
}
