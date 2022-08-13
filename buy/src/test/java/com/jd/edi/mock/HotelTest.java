package com.jd.edi.mock;


import com.jd.edi.es.EsClient;
import com.jd.edi.mapper.HotelMapper;
import com.jd.edi.service.HotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HotelTest {

    @Autowired
    private HotelService hotelService;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private EsClient esClient;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(HotelTest.class);
    }

    @Test
    public void test1() {

    }

}
