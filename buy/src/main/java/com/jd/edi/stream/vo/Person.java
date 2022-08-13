package com.jd.edi.stream.vo;

public class Person {

    private String name;  // 姓名
    private int salary; // 薪资
    private String sex; //性别
    private int age;
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.area = area;
    }

    public Person(String name, int salary, int age, String sex,  String area) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.age = age;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
