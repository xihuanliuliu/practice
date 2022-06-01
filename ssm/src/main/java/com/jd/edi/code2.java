package com.jd.edi;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class code2 {

    public static void main(String[] args) {
        int[] nums = {30,40,50,60};
        int[] res = dailyTemperatures2(nums);
        System.out.println(Arrays.toString(res));
    }

    public static int[] dailyTemperatures2(int[] temperatures) {
        // 使用一个栈存储下标
        Deque<Integer> integerDeque = new LinkedList<Integer>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            // 如果栈为空，将当前下标入栈，
            // 如果i>peak的值，则表示就是当前是第一个大于他的值，需要
            // 如果i < peak的值，则表示不满足，需要继续往后遍历
            // 否则将i-peak的值放到数组中
            // 栈顶元素
            int currentVal = temperatures[i];
            while (!integerDeque.isEmpty() && currentVal > temperatures[integerDeque.peek()]) {
                Integer pop = integerDeque.pop();
                result[pop] = i - pop;
            }
            integerDeque.push(i);
        }
        return result;
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        // 遍历,用当前值跟后续的值进行比较，如果遇到大于他的值，就是他。否则就是为0
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length - 1; i++) {
            int index = 0;
            for (int j = i+1; j < temperatures.length; j++) {
                index++;
                if (temperatures[i] < temperatures[j]) {
                    result[i] = index;
                    break;
                }
            }
        }
        return result;
    }
}
