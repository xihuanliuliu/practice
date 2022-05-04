package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Category;
import com.jd.edi.mapper.CategoryMapper;
import com.jd.edi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateCategory(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        // 写条件的时候需要注意，如果name是唯一索引，那么不要更新
        queryWrapper.lambda().eq(Category::getId, category.getId());
        return categoryMapper.update(category, queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteCategory(Long categoryId) {
        return categoryMapper.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryMapper.selectOne(new QueryWrapper<Category>().lambda().eq(Category::getId, categoryId));
    }

    @Override
    public List<Category> getCategoryList(Integer type) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // String.value(type);这样是无法盘判空的
        if (type != null) {
            queryWrapper.eq(Category::getType, type);
        }
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Category> getCategory(Integer page, Integer pageSize, String name, Integer type, Integer sort) {
        Page<Category> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Category::getName, name);
        lambdaQueryWrapper.orderByAsc(Category::getSort);
        return categoryMapper.selectPage(categoryPage, lambdaQueryWrapper);
    }
}
