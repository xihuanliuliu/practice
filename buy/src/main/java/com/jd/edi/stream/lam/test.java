package com.jd.edi.stream.lam;

public class test {


    public static void test1() {
        GreetingMessage message = new GreetingMessage() {
            @Override
            public void sayMes(String str) {
                System.out.println("-------------" +str);
            }
        };
        message.sayMes("sda");

        GreetingMessage message1 = (str -> {
            System.out.println(str + "-----------");
        });
        message1.sayMes("aa");
    }

    public static void test2() {
        MathOperator operator = new MathOperator() {
            @Override
            public int operator(int a, int b) {
                return a-b;
            }
        };
        int res = operator.operator(1, 0);
        System.out.println(res);
    }

    public static void main(String[] args) {
        test2();



    }
}
