package com.ai.aif.gitai;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

@SpringBootTest
public class TestJsonImpl {




    @Resource
    private ObjectMapper objectMapper;

    /**
     * JSON字符串->对象
     */
    @Test
    public void testJson1() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Car car = objectMapper.readValue(carJson, Car.class);
        String json =
                "[\"a1\", \"a2\"]";
        System.out.println(json);
        //System.out.println(car.toString());

    }

    /**
     * JSON 字符输入流->对象
     */
    @Test
    public void testJson2() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        Reader reader = new StringReader(carJson);
        Car car = objectMapper.readValue(reader, Car.class);
        System.out.println(car.toString());

    }

    /**
     * JSON文件->对象
     */
    @Test
    public void testJson3() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        File file = new File("data/car.json");
        Car car = objectMapper.readValue(file, Car.class);
        System.out.println(car.toString());
    }


    /**
     * JSON二进制数组->对象
     */
    @Test
    public void testJson4() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        byte[] bytes = carJson.getBytes("UTF-8");
        Car car = objectMapper.readValue(bytes, Car.class);
        System.out.println(car.toString());
    }

    /**
     * JSON数组字符串->对象数组
     */
    @Test
    public void testJson5() throws IOException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Car[] car = objectMapper.readValue(jsonArray, Car[].class);
        System.out.println(car.toString());
    }

    /**
     * JSON字符串->Map
     */
    @Test
    public void testJson6() throws IOException {
        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> car = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String,Object>>(){});
       for (String key : car.keySet()) {
           System.out.println("key: " + key + ", value: " + car.get(key));
       }
    }
}
