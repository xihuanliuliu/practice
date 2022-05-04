package com.jd.edi.service;

import com.jd.edi.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    void saveOrderDetail(List<OrderDetail> orderDetails);
}
