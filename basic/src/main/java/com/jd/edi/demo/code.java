package com.jd.edi.demo;

public class code {

    public static void main(String[] args) {
        int a[][] = new int[5][2];
        for (int i = 0; i <5; i++) {
        a[i][0] = i;
         if (i >0) a[i][1] = a[i][0] + a[i-1][1];
            }
            System.out.println(a[4][1]);
        }
}
