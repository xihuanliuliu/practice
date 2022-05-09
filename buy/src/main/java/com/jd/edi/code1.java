package com.jd.edi;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 员工工号
 */
public class code1 {

    public static int cal(int x, int y) {
        Deque<Object> objects = new LinkedList<>();
        int res = 1;
        int temp = (int) Math.pow(26, y);
        while (temp * Math.pow(10, res) < (long) x) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(cal(26, 1));
    }
}
