package com.jd.edi;

import java.util.*;

public class code1 {
    public static void showBug(int[][] nums) {
        /**
         * hashmap<根的值，list<这个的根的子节点的数组【节点1，节点1的位置】【节点2，节点2的位置】>
         *     遍历数组 都存进去hashmap
         *
         **/
        HashMap<Integer, List<int[]>> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i][1])) {
                List<int[]> list = hm.get(nums[i][1]);
                list.add(new int[]{nums[i][0], nums[i][2]});
                hm.put(nums[i][1], list);
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{nums[i][0], nums[i][2]});
                hm.put(nums[i][1], list);
            }
        }
        /**
         * [子节点，父节点，子节点在父节点的位置]
         * 只在父节点的位置出现过 没有在子节点的位置出现过的节点值  就是根节点
         */
        int root = -1;
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hs.add(nums[i][0]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!hs.contains(nums[i][1])) {
                root = nums[i][1];
            }
        }
        System.out.println("root " + root);
        /**
         * 通过hashmap key为根节点的值  value就是 存储 当前根节点的子节点的值的数组
         * 按照子节点在根节点中的位置对数组进行排序 【根的子节点1，根的子节点1的位置】  【根的子节点2，根的子节点2的位置】。。。。
         * 再拿到 根的子节点1的值 找到  根的子节点的子节点            过程大概就是是广度遍历BFS
         */
        Deque<Integer> arr = new LinkedList<>();
        arr.add(root);
        while (arr.size() > 0) {
            int rot = arr.removeFirst();
            if (hm.containsKey(rot)) {
                List<int[]> tmp = hm.get(rot);
                Collections.sort(tmp, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                });
                for (int i = 0; i < tmp.size(); i++) {
                    arr.add(tmp.get(i)[0]);
                    System.out.println(tmp.get(i)[0]);
                }
            }
        }
    }
}
