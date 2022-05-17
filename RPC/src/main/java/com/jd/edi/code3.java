package com.jd.edi;

import java.util.ArrayList;
import java.util.List;

public class code3 {
    static boolean[] visited;
    static ArrayList<List<Integer>> res;
    static ArrayList<Integer> list;

    public static void resolve(int n, int[] zs) {
        if (2000 % (n + 1) != 0)
            return;
        int gap = 2000 / (n + 1);
        res = new ArrayList<>();
        list = new ArrayList<>();

        int[] staticl = new int[zs.length];
        for (int i = 0; i < staticl.length; i++) {
            staticl[i] = (i + 1) * gap;
        }
        visited = new boolean[staticl.length];
        trace(zs, staticl);
        for (List<Integer> list : res
        ) {
            for (Integer i : list)
                System.out.print(i + " ");
            System.out.println();
        }


    }

    public static void trace(int[] zs, int[] statical) {
        if (list.size() == zs.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < zs.length; i++) {
            if (visited[i] == false) {
                if ((i == zs.length - 1) || (i < zs.length - 1 && zs[i] < statical[i] && zs[i + 1] > statical[i])) {
                    visited[i] = true;
                    list.add(i + 1);
                    int tmp = zs[i];
                    zs[i] = statical[i];
                    trace(zs, statical);
                    visited[i] = false;
                    list.remove(list.size() - 1);
                    zs[i] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        resolve(4, new int[]{50, 600, 700, 1000});
        System.out.println("---");
        resolve(3, new int[]{50, 60, 1000});
    }

}
