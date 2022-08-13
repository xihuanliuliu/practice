package com.jd.edi.stream;

import com.jd.edi.stream.vo.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamSort {

    /**
     * sorted()：自然排序
     * sorted(Comparator c)：定制排序
     */

    public static void sorted(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream()
                .sorted() //comparaTo()
                .forEach(System.out::println);
    }


    static List<Employee> emps = Arrays.asList(
            new Employee(101, "Z3", 19, 9999.99),
            new Employee(102, "L4", 20, 7777.77),
            new Employee(103, "W5", 20, 6666.66),
            new Employee(104, "Tom", 44, 1111.11),
                new Employee(102, "L4", 20, 7778.77),
            new Employee(105, "Jerry1", 60, 4444.44),
            new Employee(105, "Jerry2", 60, 4445.44),
            new Employee(105, "Jerry3", 60, 4446.44),
            new Employee(105, "Jerry4", 60, 4447.44)

    );


    /**
     * 根据某个属性进行排序
     */
    public static void test01() {
        // reversed倒排序--从大到小
        emps.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).forEach(
                System.out::println
        );
        // 从小到大
        emps.stream().sorted(Comparator.comparing(Employee::getSalary)).forEach(
                System.out::println
        );
    }

    /**
     * 连续排序
     */
    public static void test02() {
        emps.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(
                Comparator.comparingDouble(Employee::getSalary))
        ).forEach(System.out::println);
    }

    public static void test05(){
        emps.stream()
                .sorted((e1, e2) -> { //compara()
                    if (e1.getAge().equals(e2.getAge())){
                        return e1.getSalary().compareTo(e2.getSalary());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
//        sorted();
        System.out.println("---------------");
//        test05();
        System.out.println("--------------------");
        test02();
    }

}
