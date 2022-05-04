package com.jd.edi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByEmployeeId(Long employeeId);

    Employee getEmployeeByEmployeeUserName(String username);

    Page<Employee> getEmployees(int page, int size, String name);

    int updateEmployeeStatus(Long id, Employee employee);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(String username);

}
