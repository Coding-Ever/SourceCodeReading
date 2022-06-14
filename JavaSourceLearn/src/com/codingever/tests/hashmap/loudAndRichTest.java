package com.codingever.tests.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class loudAndRichTest {
    private Map<Integer, Set<Integer>> personOfRicher = new HashMap<Integer, Set<Integer>>();
    public void loudAndRich(int[][] richer, int[] quiet){
        int len = quiet.length;
        //if(len <= 1) return quiet;
        int[] ans = new int[len];


        // 初始化
        for (int i = 0; i < richer.length; i++) {
            if (!personOfRicher.containsKey(richer[i][1])){//不包含
                Set<Integer> personSet = new HashSet<>();
                personSet.add(richer[i][0]);
                personSet.add(richer[i][1]);
                personOfRicher.put(richer[i][1], personSet);
//            } else {// 包含
//                Set<Integer> personSet = personOfRicher.get(richer[i][1]);
//                personSet.add(richer[i][0]);
//                personOfRicher.put(richer[i][1], personSet);
            }
            if (personOfRicher.containsKey(richer[i][0])){
                Set<Integer> setAdd = personOfRicher.get(richer[i][0]);
                Set<Integer> set = personOfRicher.get(richer[i][1]);
                set.addAll(setAdd);
                personOfRicher.put(richer[i][1], set);
            }

        }



        //return ans;
    }

    public static void main(String[] args) {
        loudAndRichTest lar = new loudAndRichTest();
        int[][] richer = new int[][]{{1, 0}, {2, 1}, {3, 1},{3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
        lar.loudAndRich(richer, quiet);
        System.out.println(lar.personOfRicher.toString());
    }
}
