package com.jd.edi;


import java.util.ArrayList;
import java.util.List;

/**
 * 计算最高总分数
 */
public class code2 {

    public static int calScore(int[] nums) {
        int totalT = 0;
        int totalN = 0;
        int res = 0;
        List<Integer> totalScore = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                res += nums[i];
                totalScore.add(res);
            } else if (nums[i] < 0) {
                totalT = res + nums[i];
                if (i - 3 >= 0) {
                    totalN = totalScore.get(i - 3);
                } else {
                    totalN = 0;
                }
                res = Math.max(totalN, totalT);
                totalScore.add(res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,-5,-6,4,3,6,-2};
        System.out.println(calScore(nums));
    }

}
