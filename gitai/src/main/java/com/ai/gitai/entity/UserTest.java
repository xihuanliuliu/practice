package com.ai.gitai.entity;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTest {

    public static void convert(List<User> userList, List<Dog> dogList, Cat cat) {
        // 为空有几种，一种是未初始化： List<String> list  = null; 常见的空指针异常，没有初始化但是进行用
        // 另一种是初始化了，但是没有值， List<String> list = new Array<>();
        // User user = new User; user.getName = null;不会报错但是值为null
        // User user = null; user.getName 抛出空指针异常
        if (!CollectionUtils.isEmpty(userList)) {
            System.out.println("user list ");
            userList.forEach( temp -> {
                System.out.println("---------");
                Dog dog = new Dog();
                dog.setName(temp.getName());
                dog.setAge(temp.getAge());
                dog.setAddress(cat.getAddress());
                dogList.add(dog);
                System.out.println("dog: " + dog.toString());
            });
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
//        User user = new User();
//        user.setAddress("12");
//        user.setName("zhangsan");
//        User user1 = new User();
//        user1.setAge("123");
//        user1.setName("zhangjing");
//        users.add(user);
//        users.add(user1);
        List<Dog> dogList = new ArrayList<>();
        convert(users, dogList, new Cat().setAddress("cat address"));
    }
}
