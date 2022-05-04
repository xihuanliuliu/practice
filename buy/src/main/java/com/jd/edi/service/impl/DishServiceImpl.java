package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.entity.Category;
import com.jd.edi.entity.Dish;
import com.jd.edi.entity.DishFlavor;
import com.jd.edi.exception.CategoryException;
import com.jd.edi.mapper.DishFlavorMapper;
import com.jd.edi.mapper.DishMapper;
import com.jd.edi.service.CategoryService;
import com.jd.edi.service.DishFlavorService;
import com.jd.edi.service.DishService;
import com.jd.edi.vo.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl implements DishService {


    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorService dishFlavorService;
    @Resource
    private CategoryService categoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveDish(DishDto dishDto) {
        // 先保存到dish
        dishMapper.insert(dishDto);
        Long dishId = dishDto.getId();

        List<DishFlavor> dishFlavors = dishDto.getFlavors();
        // 以后不要写 for 循环了。 写stream
        dishFlavors = dishFlavors.stream().map(item -> {
            item.setDishId(dishId);
            item.setIsDeleted(0);
            item.setUpdateUser(AuthContext.currentUser());
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateUser(AuthContext.currentUser());
            item.setCreateTime(LocalDateTime.now());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(dishFlavors);
    }

    @Override
    public Integer countDishByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getCategoryId, categoryId);
        return dishMapper.selectCount(queryWrapper);
    }

    @Override
    public Dish getDishById(Long id) {
        return dishMapper.selectById(id);
    }

    @Override
    public Page<Dish> getDishPage(Integer page, Integer pageSize, String name) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        return dishMapper.selectPage(pageInfo, queryWrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDish(List<Long> ids) {
        //1 先查询是否能删除
        Integer count = countDishStatus(ids);
        if (count > 0) {
            throw new CategoryException("不能删除在售的菜品");
        }

        // 2.删除dish
        dishMapper.deleteBatchIds(ids);

        // 3.删除flavor
        dishFlavorService.deleteBatchFlavor(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDish(DishDto dishDto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDto, dish);
        dishMapper.updateById(dish);

        // 更新flavor

        // 1.查找到数据
        List<DishFlavor> dishFlavorList = dishFlavorService.getDishFlavors(dishDto.getId());
        // 2.删除这数据
        List<Long> dishFlavorIds = dishFlavorList.stream().map((item)->{
            Long id = item.getId();
            return id;
        }).collect(Collectors.toList());
        // 注意：如果列表没有数据，不要删除，要不是sql异常
        if (dishFlavorList.size() > 0) {
            dishFlavorService.deleteBatchFlavor(dishFlavorIds);
        }
        // 3.插入新的数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            item.setIsDeleted(0);
            item.setUpdateUser(AuthContext.currentUser());
            item.setUpdateTime(LocalDateTime.now());
            item.setCreateUser(AuthContext.currentUser());
            item.setCreateTime(LocalDateTime.now());
            return item;
        }).collect(Collectors.toList());
        log.info("update flavors: {}", flavors);
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public List<Dish> getDishListByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq(Dish::getCategoryId, categoryId);
        }
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        return dishMapper.selectList(queryWrapper);
    }

    @Override
    public List<DishDto> getDishDtoList(Dish dish) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishMapper.selectList(queryWrapper);

        List<DishDto> dishDtoList = list.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            List<DishFlavor> dishFlavors = dishFlavorService.getDishFlavors(item.getId());
            dishDto.setFlavors(dishFlavors);
            Category category = categoryService.getCategoryById(item.getCategoryId());
            dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());

        return dishDtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDishStatus(List<Long> ids, Integer status) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId, ids);
        Dish dish = new Dish();
        dish.setStatus(status);
        dishMapper.update(dish, queryWrapper);
    }

    @Override
    public Integer countDishStatus(List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId, ids);
        queryWrapper.eq(Dish::getStatus, 1);
        return dishMapper.selectCount(queryWrapper);
    }

}
