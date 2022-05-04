package com.jd.edi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.common.R;
import com.jd.edi.entity.Category;
import com.jd.edi.entity.Dish;
import com.jd.edi.exception.CategoryException;
import com.jd.edi.service.CategoryService;
import com.jd.edi.service.DishService;
import com.jd.edi.service.SetmealService;
import com.jd.edi.vo.DishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private DishService dishService;
    @Resource
    private SetmealService setmealService;
    
    @PostMapping
    public R<String> saveCategory(HttpServletRequest request, @RequestBody Category category) {
        // 添加注释
        int ret = categoryService.saveCategory(category);
        if (ret != 1){
            return R.error("添加分类失败");
        }
        return R.success("添加分类成功");
    }

    @GetMapping("/page")
    public R<Page<Category>> getCategoryPage(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String name,
                                             @RequestParam(required = false) Integer type, @RequestParam(required = false) Integer sort) {
        Page<Category> category = categoryService.getCategory(page, pageSize, name, type, sort);
        return R.success(category);
    }


    @PutMapping
    public R<String> updateCategory(@RequestBody Category category) {
        int ret = categoryService.updateCategory(category);
        if (ret != 1){
            return R.error("更新分类失败");
        }
        return R.success("更新分类成功");
    }

    @GetMapping("/list")
    public R<List<Category>> getCategoryList(Category category) {
        List<Category> list = categoryService.getCategoryList(category.getType());
        return R.success(list);
    }

    @DeleteMapping
    public R<String> deleteCategory(@RequestParam Long id){
        // 需要判断这个分析下是否包含了其他信息，如果包含，需要提示
        int countDish = dishService.countDishByCategoryId(id);
        if (countDish > 0) {
            throw new CategoryException("此分类下包含["+ countDish +"]个Dish");
        }
        int countSetmeal = setmealService.countSetmealByCategoryId(id);
        if (countSetmeal > 0) {
            throw new CategoryException("此分类下包含["+ countSetmeal +"]个Setmeal");
        }
        int ret = categoryService.deleteCategory(id);
        if (ret != 1){
            return R.error("更新分类失败");
        }
        return R.success("更新分类成功");
    }


}
