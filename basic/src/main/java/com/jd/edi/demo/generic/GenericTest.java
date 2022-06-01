package com.jd.edi.demo.generic;

public class GenericTest {

    public static void main(String[] args) {
        ResponseDemo responseDemo = new ResponseDemo();
        responseDemo.setData("1");
        System.out.println(responseDemo.getData());
        ResponseDemo<Integer> responseDemo1 = new ResponseDemo<>();
        //responseDemo1.setData("1"); 这里会报错，因为创建对象时指定了data的类型
        System.out.println(responseDemo1.getData());
        System.out.println("----------------------");

        //class InterfaceImpl2<T extends InterfaceName>
        // 错误，里面的类型是InterfaceName的子类
        //InterfaceImpl2<String> stringInterfaceImpl2 = new InterfaceImpl2<String>();
        InterfaceImpl2<InterfaceImpl> sss = new InterfaceImpl2<>();


    }
}
