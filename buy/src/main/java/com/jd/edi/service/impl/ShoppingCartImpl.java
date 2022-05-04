package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.entity.ShoppingCart;
import com.jd.edi.mapper.ShoppingCartMapper;
import com.jd.edi.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ShoppingCartImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Transactional
    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        // 1.查询
        ShoppingCart cart = getShoppingCart(shoppingCart);
        // 2.如果没有则添加
        if (cart == null) {
            shoppingCartMapper.insert(shoppingCart);
        } else {
            // 3.如果有则添加数量
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.updateById(cart);
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCartList(Long userId) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        return shoppingCartMapper.selectList(queryWrapper);
    }

    @Override
    public void subShoppingCart(ShoppingCart shoppingCart) {
        // 1.先去查有没有
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(shoppingCart.getDishId() != null, ShoppingCart::getDishId, shoppingCart.getDishId());
        queryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        queryWrapper.eq(ShoppingCart::getUserId, AuthContext.currentUser());
        ShoppingCart queryShoppingCart = shoppingCartMapper.selectOne(queryWrapper);
        // 2.如果有则减去1
        if (queryShoppingCart != null) {
            if (queryShoppingCart.getNumber() > 1) {
                queryShoppingCart.setNumber(queryShoppingCart.getNumber() - 1);
                shoppingCartMapper.updateById(queryShoppingCart);
            } else if (queryShoppingCart.getNumber() > 0){
                // 3.没有则删除
                shoppingCartMapper.deleteById(queryShoppingCart);
            }
        }
    }

    @Override
    public ShoppingCart getShoppingCart(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, shoppingCart.getUserId());
        queryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        queryWrapper.eq(shoppingCart.getDishId() != null, ShoppingCart::getDishId, shoppingCart.getDishId());
        ShoppingCart cart = shoppingCartMapper.selectOne(queryWrapper);
        return cart;
    }
}
