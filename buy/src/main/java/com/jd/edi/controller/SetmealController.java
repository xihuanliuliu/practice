package com.jd.edi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.common.R;
import com.jd.edi.entity.Category;
import com.jd.edi.entity.Setmeal;
import com.jd.edi.entity.SetmealDish;
import com.jd.edi.service.CategoryService;
import com.jd.edi.service.SetmealDishService;
import com.jd.edi.service.SetmealService;
import com.jd.edi.vo.SetmealDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {


    @Resource
    private SetmealService setmealService;
    @Resource
    private SetmealDishService setmealDishService;
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public R<String> saveSetmeal(@RequestBody SetmealDto setmealDto) {
        setmealService.saveSetmeal(setmealDto);
        return R.success("添加套餐成功");
    }


    @GetMapping("/page")
    public R<Page<SetmealDto>> page(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String name){
        Page<Setmeal> setmealPage = setmealService.getSetmealPage(page, pageSize, name);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        BeanUtils.copyProperties(setmealPage, setmealDtoPage, "records");

        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto>  setmealDtos = records.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getCategoryById(categoryId);
            setmealDto.setCategoryName(category.getName());
            return setmealDto;

        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(setmealDtos);
        return R.success(setmealDtoPage);
    }

    @GetMapping("/{id}")
    public R<SetmealDto> getSetmeal(@PathVariable Long id) {
        Setmeal setmeal = setmealService.getSetmeal(id);
        List<SetmealDish> setmealDish = setmealDishService.getSetmealDish(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        setmealDto.setSetmealDishes(setmealDish);
        return R.success(setmealDto);
    }


    @PutMapping
    public R<String> updateSetmeal(@RequestBody SetmealDto setmealDto) {
        setmealService.updateSetmeal(setmealDto);
        return R.success("更新成功");
    }

    @DeleteMapping()
    public R<String> deleteSetmeal(@RequestParam List<Long> ids) {
        setmealService.deleteSetmeal(ids);
        return R.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public R<String> updateSetmealStatus(@PathVariable Integer status, @RequestParam List<Long> ids){
        setmealService.updateSetmealStatus(ids, status);
        return R.success(status==1? "启售":"停售" + "成功");
    }
}
