package com.jd.edi.service;

import com.jd.edi.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {

    void saveBatch(List<DishFlavor> flavors);

    void deleteBatchFlavor(List<Long> dishId);

    List<DishFlavor> getDishFlavors(Long dishId);

    void updateDishFlavor(List<DishFlavor> flavors);
}
