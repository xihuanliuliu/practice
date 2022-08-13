package com.jd.edi.controller;

import com.jd.edi.entity.Hotel;
import com.jd.edi.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);


    @Autowired
    private HotelService hotelService;

    @GetMapping("/data")
    public void getData() {
        List<Hotel> hotels = hotelService.queryAll();
        for (Hotel hotel : hotels) {
            System.out.println(hotel.toString());
        }

    }


}
