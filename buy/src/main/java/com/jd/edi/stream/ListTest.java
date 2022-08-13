package com.jd.edi.stream;

import com.jd.edi.stream.vo.Person;

import java.util.*;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args) {

//        List<Person> personList = new ArrayList<Person>();
//        personList.add(new Person("Tom", 8900, "male", "New York"));
//        personList.add(new Person("Jack", 7000, "male", "Washington"));
//        personList.add(new Person("Lily", 7800, "female", "Washington"));
//        personList.add(new Person("Anni", 8200, "female", "New York"));
//        personList.add(new Person("Owen", 9500, "male", "New York"));
//        personList.add(new Person("Alisa", 7900, "female", "New York"));

//
//        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
//
//        // 遍历输出符合条件的元素
//        list.stream().filter(x -> x > 6).forEach(System.out::println);
//        list.stream().filter(x -> x > 5).forEach(System.out::println);
//        // 匹配第一个
//        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
//        // 匹配任意（适用于并行流）
//        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
//        // 是否包含符合特定条件的元素
//        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
//        System.out.println("匹配第一个值：" + findFirst.get());
//        System.out.println("匹配任意一个值：" + findAny.get());
//        System.out.println("是否存在大于6的值：" + anyMatch);



        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 排序


    }
}
