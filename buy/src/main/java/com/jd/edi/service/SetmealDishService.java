package com.jd.edi.service;

import com.jd.edi.entity.SetmealDish;

import java.util.List;

public interface SetmealDishService {

    void saveSetmealDish(List<SetmealDish> setmealDishList);

    List<SetmealDish> getSetmealDish(Long setmealId);

    void deleteSetmealDish(List<Long> setmealId);

}
