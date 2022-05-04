package com.jd.edi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Setmeal;
import com.jd.edi.vo.SetmealDto;

import java.util.List;

public interface SetmealService {

    Integer countSetmealByCategoryId(Long categoryId);

    List<Setmeal> getSetmealList(Setmeal setmeal);

    void saveSetmeal(SetmealDto setmealDto);

    Page<Setmeal> getSetmealPage(Integer page, Integer pageSize, String name);

    Setmeal getSetmeal(Long id);

    void updateSetmeal(SetmealDto setmealDto);

    void deleteSetmeal(List<Long> ids);

    Integer countSetmealStatus(List<Long> ids);

    void updateSetmealStatus(List<Long> ids, Integer status);


}
