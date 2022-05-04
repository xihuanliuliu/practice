package com.jd.edi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.edi.common.R;
import com.jd.edi.entity.Employee;
import com.jd.edi.service.EmployeeService;
import com.jd.edi.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
// 511621199601281234

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController extends AbstractController {

    @Resource
    private EmployeeService employeeService;


    /**
     * 分页
     */
    @GetMapping("/page")
    public R<Page<Employee>> getEmployeeList(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String name) {
        Page<Employee> employees = employeeService.getEmployees(page, pageSize, name);
        return R.success(employees);
    }

    @GetMapping("/{employeeId}")
    public R<Employee> getEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        return R.success(employee);
    }

    @PostMapping("")
    public R<String> saveEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("add employee");
        // 密码
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(password);
        // 创建时间
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        // 创建人
       // employee.setCreateUser(getCurrentUserId(request));
        //employee.setUpdateUser(getCurrentUserId(request));
        // 状态1
        employee.setStatus(1);
        log.info("employee info : {}", employee.toString());
        int i = employeeService.addEmployee(employee);
        if (i != 1){
            return R.error("添加员工失败");
        }
        return R.success("添加员工成功");
    }



    @DeleteMapping(path = "/{userId}")
    public R<String> deleteEmployee(HttpServletRequest request, @PathVariable String userId) {
        int i = employeeService.deleteEmployee(userId);
        if (i != 1) {
            return R.error("删除用户失败");
        }
        return R.success("删除成功");
    }



    @PutMapping()
    public R<String> updateEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        Employee emp = employeeService.getEmployeeByEmployeeId(employee.getId());
        if (emp == null) {
            return R.error("没有此用户");
        }
        // 更新者
       // employee.setUpdateUser(getCurrentUserId(request));
        // 更新时间
       // employee.setUpdateTime(LocalDateTime.now());
        int ret = employeeService.updateEmployee(employee);
        if (ret != 1) {
            return R.error("更新用户失败");
        }
        return R.success("更新用户失败");
    }





    @PostMapping(path = "/login")
    public R<Employee> login (HttpServletRequest request, @RequestBody LoginUser loginUser){
        if(loginUser == null) {
            return R.error("用户未登录");
        }
        Employee employee = employeeService.getEmployeeByEmployeeUserName(loginUser.getUsername());
        if(employee == null) {
            return R.error("用户名或者密码错误");
        }
        String userPassword = DigestUtils.md5DigestAsHex(loginUser.getPassword().getBytes());
        if (!employee.getPassword().equals(userPassword)) {
            return R.error("用户名或者密码错误");
        }
        if(employee.getStatus() != 1) {
            return R.error("用户名已经被禁用");
        }
        request.getSession().setAttribute("employee", employee.getId());
        return R.success(employee);
    }

    /**
     * 退出登录
     */

    @PostMapping(path = "/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出登录成功");
    }
}
