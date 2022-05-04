package com.jd.edi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.common.R;
import com.jd.edi.entity.Category;
import com.jd.edi.entity.Dish;
import com.jd.edi.entity.DishFlavor;
import com.jd.edi.service.CategoryService;
import com.jd.edi.service.DishFlavorService;
import com.jd.edi.service.DishService;
import com.jd.edi.vo.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;
    @Resource
    private DishFlavorService dishFlavorService;
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public R<String> saveDish(@RequestBody DishDto dishDto) {
        dishService.saveDish(dishDto);
        return R.success("添加菜品成功");
    }

    @GetMapping("/page")
    public R<Page<DishDto>> getDishPage(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String name) {
        Page<Dish> dishPage = dishService.getDishPage(page, pageSize, name);

        /**
         * 需要查询另外一个表的数据进行拼接
         */
        Page<DishDto> dishDtoPage = new Page<>();
        // copy数据，除了records-records的数据由我们自己构造
        BeanUtils.copyProperties(dishPage, dishDtoPage, "records");

        List<Dish> records = dishPage.getRecords();

        List<DishDto> dishDtoList = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getCategoryById(categoryId);
            dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(dishDtoList);
        return R.success(dishDtoPage);
    }


    @GetMapping("/{id}")
    public R<DishDto> getDishDto(@PathVariable Long id) {
        Dish dish = dishService.getDishById(id);
        List<DishFlavor> dishFlavors = dishFlavorService.getDishFlavors(id);
        DishDto dishDto = new DishDto();
        dishDto.setFlavors(dishFlavors);
        BeanUtils.copyProperties(dish, dishDto);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> updateDish(@RequestBody DishDto dishDto){
        dishService.updateDish(dishDto);
        return R.success("更新成功");
    }

    @DeleteMapping
    public R<String> deleteDish(@RequestParam List<Long> ids) {
        dishService.deleteDish(ids);
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<Dish>> getDishByCategory(@RequestParam String categoryId) {
        List<Dish> dishList = dishService.getDishListByCategoryId(categoryId);
        return R.success(dishList);
    }


    @PostMapping("/status/{status}")
    public R<String> updateDishStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        dishService.updateDishStatus(ids, status);
        return R.success("修改状态成功");
    }

}
