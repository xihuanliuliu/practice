package com.jd.edi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Dish;
import com.jd.edi.vo.DishDto;

import java.util.List;


public interface DishService {

    void saveDish(DishDto dishDto);

    Integer countDishByCategoryId(Long categoryId);

    Dish getDishById(Long id);

    Page<Dish> getDishPage(Integer page, Integer pageSize, String name);

    void deleteDish(List<Long> ids);

    void updateDish(DishDto dishDto);

    List<Dish> getDishListByCategoryId(String categoryId);

    void updateDishStatus(List<Long> ids, Integer status);

    Integer countDishStatus(List<Long> ids);
}
