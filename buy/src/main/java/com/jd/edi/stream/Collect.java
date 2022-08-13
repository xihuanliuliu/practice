package com.jd.edi.stream;

import com.jd.edi.stream.vo.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Collect {


    public static void main(String[] args) {

        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Product> prodList = new ArrayList();
        prodList.add(prod1);
        prodList.add(prod2);
        prodList.add(prod3);
        prodList.add(prod4);
        prodList.add(prod5);

        Collectors.groupingBy(Product::getCategory);
        Map<String, List<Product>> prodMap= prodList.stream().collect(Collectors.groupingBy(Product::getCategory));
//        {"啤酒":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10},
//        {"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15}],

//        "零食":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.5},
//        {"category":"零食","id":2,"name":"饼干","num":2,"price":20},
//        {"category":"零食","id":3,"name":"月饼","num":3,"price":30}]}

        Map<String, List<Product>> prodMap1 = prodList.stream().collect(Collectors.groupingBy(item -> item.getCategory() + "_" + item.getName()));

        //{"零食_月饼":[{"category":"零食","id":3,"name":"月饼","num":3,"price":30}],
        // "零食_面包":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.5}],
        // "啤酒_百威啤酒":[{"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15}],
        // "啤酒_青岛啤酒":[{"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10}],
        // "零食_饼干":[{"category":"零食","id":2,"name":"饼干","num":2,"price":20}]}


        Map<String, List<Product>> prodMap2 = prodList.stream().collect(Collectors.groupingBy(item -> {
            if(item.getNum() < 3) {
                return "3";
            }else {
                return "other";
            }
        }));

        //{"other":[{"category":"零食","id":3,"name":"月饼","num":3,"price":30},
        // {"category":"啤酒","id":4,"name":"青岛啤酒","num":3,"price":10},
        // {"category":"啤酒","id":5,"name":"百威啤酒","num":10,"price":15}],
        // "3":[{"category":"零食","id":1,"name":"面包","num":1,"price":15.5},
        // {"category":"零食","id":2,"name":"饼干","num":2,"price":20}]}
    }
}
