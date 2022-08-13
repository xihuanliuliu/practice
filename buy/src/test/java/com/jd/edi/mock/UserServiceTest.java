package com.jd.edi.mock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.entity.User;
import com.jd.edi.mapper.UserMapper;
import com.jd.edi.service.UserService;
import com.jd.edi.service.impl.MockTestServiceImpl;
import com.jd.edi.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Before
    public void before() {
        System.out.println("初始化");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test1() {
        String phone = "phone";
        User user = new User();
        user.setPhone("phone");
        user.setId(1L);
        user.setStatus(1);
        user.setSex("man");
        Mockito.when(userService.getUserByPhone(phone)).thenReturn(user);
        User userByPhone = userService.getUserByPhone(phone);
        System.out.println(userByPhone.toString());
    }

    @Test
    public void test2() {
        User user = new User();
        user.setPhone("phone");
        user.setId(1L);
        user.setStatus(1);
        user.setSex("man");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Mockito.when(userMapper.selectOne(queryWrapper)).thenReturn(user);
        User selectOne = userMapper.selectOne(queryWrapper);
        System.out.println("mapper: " + selectOne.toString());

    }

    /**
     * 在实际的开发中----注入的方式为 将内部bean使用@Mock注解。当前实现类使用@InjectMocks，不要对接口使用。
     *
     * 当前实现类不要使用Mockito.when()---当前类走的是实际流程
     */
    @Test
    public void test3() {
        String phone = "phone";
        User user = new User();
        user.setPhone("phone");
        user.setId(1L);
        user.setStatus(1);
        user.setSex("man");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone,phone);
        Mockito.when(userMapper.selectOne(Mockito.anyObject())).thenReturn(user);
        System.out.println(userMapper.selectOne(Mockito.anyObject()).toString());

        User userByPhone = userServiceImpl.getUserByPhone(phone);
        System.out.println(userByPhone.toString());
    }



    @InjectMocks
    private MockTestServiceImpl mockTestService;


    @Test
    public void test4() {
        String phone = "phone";
        User user = new User();
        user.setPhone("phone");
        user.setId(1L);
        user.setStatus(1);
        user.setSex("man");

        // 打桩----打桩并没有不算调用
        Mockito.when(userMapper.selectOne(Mockito.anyObject())).thenReturn(user);
        // 调用userMapper.selectOne方法
        System.out.println(userMapper.selectOne(Mockito.anyObject()).toString());
        // 验证是否调用了userMapper.selectOne一次；如果满足则通过，否则报错
        Mockito.verify(userMapper, Mockito.times(1)).selectOne(Mockito.anyObject());

        User selectUserByPhone = mockTestService.selectUserByPhone(phone);
        System.out.println(selectUserByPhone.toString());

        // doThrow(new RuntimeException()).when(mockedList).clear();
        // doThrow(new 异常).when(对象).方法();
        Mockito.doThrow(new RuntimeException()).when(userMapper).selectOne(Mockito.any());
    }













}
