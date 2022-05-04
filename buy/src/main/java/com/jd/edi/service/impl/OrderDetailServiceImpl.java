package com.jd.edi.service.impl;

import com.jd.edi.entity.OrderDetail;
import com.jd.edi.mapper.OrderDetailMapper;
import com.jd.edi.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrderDetail(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            orderDetailMapper.insert(orderDetail);
        }
    }
}
