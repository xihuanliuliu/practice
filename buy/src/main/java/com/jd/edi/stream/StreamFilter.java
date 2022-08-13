package com.jd.edi.stream;

import com.jd.edi.stream.vo.Employee;

import java.util.Arrays;
import java.util.List;

public class StreamFilter {

    /**
     * filter：接收 Lambda ，从流中排除某些元素
     *
     * limit：截断流，使其元素不超过给定数量
     *
     * skip(n)：跳过元素，返回一个舍弃了前n个元素的流；若流中元素不足n个，则返回一个空流；与 limit(n) 互补
     *
     * distinct：筛选，通过流所生成的 hashCode() 与 equals() 取除重复元素
     */


    static List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(105, "Jerry", 60, 4444.44)
    );

    public static void main(String[] args) {
        emps.stream()
                .filter((x) -> x.getAge() > 35)
                .limit(3) //短路？达到满足不再内部迭代
                .distinct()
                .skip(1)
                .forEach(System.out::println);
        emps.stream()
                .filter((x) -> x.getAge() > 35)
                .forEach(System.out::println);
    }
}
