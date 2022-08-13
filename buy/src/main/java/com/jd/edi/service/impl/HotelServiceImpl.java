package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.annotation.AutoIdempotent;
import com.jd.edi.entity.Hotel;
import com.jd.edi.entity.HotelDoc;
import com.jd.edi.es.EsClient;
import com.jd.edi.mapper.HotelMapper;
import com.jd.edi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelMapper hotelMapper;


    @Resource(name = "detailLogEs")
    private EsClient esClient;

    @Override
    public List<Hotel> queryAll() {
        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda();
        return hotelMapper.selectList(queryWrapper);
    }

    @Override
    public void insetDataToEs() {
        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda();
        List<Hotel> hotels = hotelMapper.selectList(queryWrapper);
        for (Hotel hotel : hotels) {
            try {
                HotelDoc doc = new HotelDoc(hotel);
                esClient.insertSingleData("hotel", doc);
            } catch (Exception e) {

            }

        }

    }
}
