package com.jd.edi.stream;

import com.jd.edi.stream.vo.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class StreamCollect {
    /**
     * 归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     * 可以将流中的数据反复结合起来，得到一个值
     *
     *
     * 收集：collect 将流转换成其他形式；接收一个 Collector 接口的实现，用于给流中元素做汇总的方法
     * @param args
     */
   static List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(103, "W5", 35, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
            new Employee(105, "Jerry", 60, 4444.44),
            new Employee(106, "Tom", 45, 444.44)
    );

    public static void main(String[] args) {
        //放入List
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-------------------");
        //放入Set
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-------------------");
        //放入LinkedHashSet
        LinkedHashSet<String> linkedHashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        linkedHashSet.forEach(System.out::println);
        System.out.println("-------------------");

        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("-------------------");
        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        System.out.println("-------------------");
        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        System.out.println("-------------------");
        //最大值
        Optional<Employee> max = emps.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());
        System.out.println("-------------------");
        //最小值
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
        System.out.println("-------------------");

        //分组
        Map<Integer, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId));
        System.out.println(map);
        System.out.println("-------------------");
        //多级分组
        Map<Integer, Map<String, List<Employee>>> mapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 35) {
                        return "开除";
                    } else {
                        return "继续加班";
                    }
                })));
        System.out.println(mapMap);
        System.out.println("-------------------");
        //分区
        Map<Boolean, List<Employee>> listMap = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 4321));
        System.out.println(listMap);

    }
}
