package com.jd.edi.demo.generic;
/**
 * java 泛型测试
 */

/**
 *
 *  一般是创建类，接口，方法时不确定内部的属性的类型
 *  类：
 *  public class ResponseDemo <T>{
 *     private Integer code;
 *     private String message;
 *     // 不确定data是什么类型
 *     private T data;
 *   }
 *
 *   接口：
 *   interface InterfaceName <T>
 *
 *   接口的实现类：
 *     1、确定类型：
 *     class InterfaceImplString implements InterfaceName<String>
 *     2. 不确定类型
 *     class InterfaceImpl<T> implements InterfaceName <T>
 *
 *    方法:
 *     private static <T> T methodName(T a, T b) {}
 *
 *
 *  限制类型的区域:
 *  1、必须是某某类的子类或 某某接口的实现类:
 *      <T extends 类 或 接口1 & 接口2>
 *  泛型中的通配符：
 *      <? extends Parent> 指定了泛型类型的上界
 *      <? super Child> 指定了泛型类型的下界
 *      <?> 指定了没有限制的泛型类型
 *     InterfaceImpl2<? extends InterfaceImpl> sss = new InterfaceImpl2<xxx>();
 *     其中的new InterfaceImpl2<xxx>()只能是InterfaceImpl本身或者其子类
 *     InterfaceImpl2<? super InterfaceImpl> sss = new InterfaceImpl2<xxx>();
 *      其中的new InterfaceImpl2<xxx>()只能是InterfaceImpl本身或者其父类
 */