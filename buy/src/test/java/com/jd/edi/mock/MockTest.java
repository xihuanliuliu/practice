package com.jd.edi.mock;

import com.jd.edi.entity.Dish;
import com.jd.edi.entity.User;
import com.jd.edi.mapper.DishMapper;
import com.jd.edi.mapper.UserMapper;
import com.jd.edi.service.DishService;
import com.jd.edi.service.impl.MockTestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
public class MockTest {

    @InjectMocks
    private MockTestServiceImpl mockTestService;

    @Mock
    private UserMapper userMapper;
    @Mock
    private DishService dishService;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test1() {
        User user = new User();
        user.setId(1L);
        user.setSex("nan");
        user.setPhone("phone");
        user.setName("name");

        Dish dish = new Dish();
        dish.setId(1L);
        dish.setCode("code");
        dish.setName("phone");
        dish.setCreateTime(LocalDateTime.now());
        System.out.println("time: " + LocalDateTime.now());
        dish.setStatus(1);

        Long userId = 1L;
        Long dishId = 1L;
        String param = "phone";

        Mockito.when(dishService.getDishById(dishId)).thenReturn(dish);

        Mockito.when(userMapper.selectOne(Mockito.any())).thenReturn(user);

        User selectUserAndDish = mockTestService.selectUserAndDish(userId, dishId, param);
        System.out.println(selectUserAndDish.toString());

    }


}
