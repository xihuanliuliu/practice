package com.jd.edi.service;

import com.jd.edi.entity.Orders;

import java.util.Objects;

public interface OrdersService {

    void submit(Orders orders);

    void saveOrder(Orders orders);
}
