package com.jd.edi.controller;

import com.jd.edi.auth.AuthContext;
import com.jd.edi.common.R;
import com.jd.edi.entity.ShoppingCart;
import com.jd.edi.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartList(AuthContext.currentUser());
        return R.success(shoppingCartList);
    }

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setUserId(AuthContext.currentUser());
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCartService.addShoppingCart(shoppingCart);
        return R.success(shoppingCart);
    }


    @PostMapping("/sub")
    public R<ShoppingCart> subShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.subShoppingCart(shoppingCart);
        return R.success(shoppingCart);
    }


}
