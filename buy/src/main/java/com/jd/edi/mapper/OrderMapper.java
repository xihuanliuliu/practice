package com.jd.edi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.edi.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}
