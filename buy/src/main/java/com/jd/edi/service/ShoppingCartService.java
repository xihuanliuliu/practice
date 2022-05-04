package com.jd.edi.service;

import com.jd.edi.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCart shoppingCart);

    List<ShoppingCart> getShoppingCartList(Long userId);

    void subShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCart(Long userId);
}
