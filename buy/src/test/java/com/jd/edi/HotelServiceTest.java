package com.jd.edi;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.edi.Application;
import com.jd.edi.entity.Hotel;
import com.jd.edi.entity.HotelDoc;
import com.jd.edi.es.EsClient;
import com.jd.edi.es.QueryCondition;
import com.jd.edi.mapper.HotelMapper;
import com.jd.edi.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class HotelServiceTest {

    private static final String index = "hotel";
    @Autowired
    private HotelService hotelService;

    @Resource(name = "detailLogEs")
    private EsClient esClient;

    @Test
    public void test() {

//        String singleDoc = esClient.getSingleDoc(index, 36934L);
//        System.out.println(singleDoc);
        QueryCondition condition = new QueryCondition();
        condition.setPageSize("0");
        condition.setPageSize("10");
        condition.setBoolKey("city");
        condition.setBoolValue("北京");
        condition.setFilterKey("starName");
        condition.setFilterName("二钻");
        esClient.queryDataByCondition(index, condition);
    }

}
