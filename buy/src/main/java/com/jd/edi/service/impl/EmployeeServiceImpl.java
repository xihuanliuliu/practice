package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Employee;
import com.jd.edi.mapper.EmployeeMapper;
import com.jd.edi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployeeByEmployeeId(Long employeeId) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Employee::getId, employeeId);
        return employeeMapper.selectOne(queryWrapper);
    }

    @Override
    public Employee getEmployeeByEmployeeUserName(String username) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Employee::getUsername, username);
        return employeeMapper.selectOne(queryWrapper);
    }

    @Override
    public Page<Employee> getEmployees(int page, int size, String name) {
        Page<Employee> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        // 过滤条件，如果是按照用户名查找的，用模糊匹配
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // 根据更新时间排序
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        Page<Employee> employeePage = employeeMapper.selectPage(pageInfo, queryWrapper);
        return employeePage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateEmployeeStatus(Long id, Employee employee) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Employee::getId, id);
        int update = employeeMapper.update(employee, queryWrapper);
        return update;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addEmployee(Employee employee) {
        int insert = employeeMapper.insert(employee);
        return insert;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateById(employee);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteEmployee(String username) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Employee::getUsername, username);
        return employeeMapper.delete(queryWrapper);
    }
}
