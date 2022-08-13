package com.jd.edi.service;

import com.jd.edi.entity.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> queryAll();

    void insetDataToEs();

}
