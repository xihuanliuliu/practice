package com.jd.edi.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Category;

import java.util.List;

public interface CategoryService {

    int saveCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(Long categoryId);

    Category getCategoryById(Long categoryId);

    List<Category> getCategoryList(Integer type);

    Page<Category> getCategory(Integer page, Integer pageSize, String name, Integer type, Integer sort);

}
