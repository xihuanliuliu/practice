package com.jd.edi.spring.stream;

import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
//        List<User> cacheKeys = new ArrayList<>();
//        cacheKeys.add(new User("u1", "v1"));
//        cacheKeys.add(new User("u2", "v2"));
//        cacheKeys.add(new User("u3", "v3"));
//        cacheKeys.add(new User("u4", "v4"));
//        cacheKeys.add(new User("u5", "v5"));
        List<String> cacheKeys = new ArrayList<>();
        cacheKeys.add("1");
        cacheKeys.add("12");


        List<Integer> strCacheKeys = cacheKeys.stream().map(Integer::parseInt).collect(Collectors.toList());

        for (Integer s : strCacheKeys) {
            System.out.println(s);
        }
    }
}
