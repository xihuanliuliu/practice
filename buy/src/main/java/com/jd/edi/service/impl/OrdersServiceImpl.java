package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.entity.*;
import com.jd.edi.exception.CategoryException;
import com.jd.edi.mapper.OrderMapper;
import com.jd.edi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private UserService userService;
    @Resource
    private AddressBookService addressBookService;
    @Resource
    private OrderDetailService orderDetailService;

    @Transactional
    @Override
    public void submit(Orders orders) {
        // 1.获取当前用户
        Long userId = AuthContext.currentUser();
        // 2.查询当前用户的购物车
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(userId);
        if (shoppingCartList == null || shoppingCartList.size() == 0) {
            throw new CategoryException("购物车数据不能为空");
        }
        // 3.查询用户数据
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new CategoryException("用户不存在");
        }
        // 4.查询用户的收货地址
        Long addressId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getAddressBookById(addressId);
        if (addressBook == null) {
            throw new CategoryException("收货地址不能为空");
        }
        // 5. 生成订单号
        long orderId = IdWorker.getId();//订单号
        // 6.构造订单明细
        AtomicInteger amount = new AtomicInteger(0);
        List<OrderDetail> orderDetails =shoppingCartList.stream().map((item)->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));

        // 7. 向订单表插入数据
        saveOrder(orders);
        // 8. 向订单明细表插入数据
        orderDetailService.saveOrderDetail(orderDetails);
        // 9. 清空购物车的数据
        shoppingCartService.deleteShoppingCart(userId);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrder(Orders orders) {
        orderMapper.insert(orders);
    }
}
