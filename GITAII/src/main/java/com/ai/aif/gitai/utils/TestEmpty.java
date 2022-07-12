package com.ai.aif.gitai.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TestEmpty {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("----------");
        } else {
            System.out.println("==============");
        }
    }

}
