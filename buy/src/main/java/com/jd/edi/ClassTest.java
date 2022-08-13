package com.jd.edi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassTest {


    public static void getField(Class<TPerson> clazz, Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        // 获取对象属性的值
        System.out.println(field.get(object));

        System.out.println(field.getName());
        System.out.println(field.getType());

        // 获取方法的注解
        Annotation[] annotations1 = clazz.getAnnotations();
        for (Annotation annotation : annotations1) {
            System.out.println("注解 " + annotation);
        }

        //获取类的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("method:" + method.getName());
            System.out.println("method2:" + method.getDeclaringClass());
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("method: " + method.getName() + " ,注解: " + annotation +
                        "," + annotation.getClass());
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        TPerson tPerson = new TPerson();
        tPerson.setAge("age");
        tPerson.setName("name1");
        getField(TPerson.class, tPerson);

    }

}
