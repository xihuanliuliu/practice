package com.ai.gitai.entity;

public class TestNull {

    public static void main(String[] args) {
        // 为空有几种，一种是未初始化： List<String> list  = null; 常见的空指针异常，没有初始化但是进行用
        // 另一种是初始化了，但是没有值， List<String> list = new Array<>();
        // User user = new User; user.getName = null;不会报错但是值为null
        // User user = null; user.getName 抛出空指针异常
        User user = null;
        if (user != null) {
            System.out.println(user.getAddress());
        }else {
            System.out.println("user = null");
        }


    }
}
