package Year_2025.May.May_3;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] tops = {1,2,1,1,1,2,2,2};
        int bottoms[] = { 2,1,2,2,2,2,2,2 };
        System.out.println(solution.minDominoRotations(tops, bottoms));
    }
}

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int topLength = tops.length, bottomLength = bottoms.length;
        if (topLength != bottomLength) return -1;
        // System.out.println("Passed the check of equal length");
        if (topLength == 1) return 0;
        // System.out.println("Length is greater than 1");
        Map<Integer, Integer> topMap = new HashMap<>();
        Map<Integer, Integer> bottomMap = new HashMap<>();
        for (int num : tops) topMap.put(num, topMap.getOrDefault(num, 0) + 1);
        for (int num : bottoms) bottomMap.put(num, bottomMap.getOrDefault(num, 0) + 1);
        int rotations = Integer.MAX_VALUE;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : topMap.keySet()) {
            if (bottomMap.containsKey(num)) {
                int f1 = topMap.get(num), f2 = bottomMap.get(num);
                if (f1 + f2 >= topLength) map.put(num, (f1 >= f2) ? true : false);
            }
        }
        if (map.size() == 0) return -1;
        // System.out.println("Top Map -> ");
        // print(topMap);
        // System.out.println("Bottom Map -> ");
        // print(bottomMap);
        // System.out.println("Possible answer Map -> ");
        // print(map);
        for (int num : map.keySet()) {
            int temp = 0;
            boolean Notpossible = false;
            boolean top = map.get(num);
            for (int i = 0; i < topLength; i++) {
                if(tops[i] == num && bottoms[i] == num) continue;
                if (tops[i] == num) { // If the number is on top 
                    if (!top) temp++; // if the maxFreq of number is bottom then flip
                } else if (bottoms[i] == num) { // If the number is on bottom
                    if (top) temp++; // if the maxFreq of number is on top then flip
                } else {
                    Notpossible = true;
                    break;
                }
            }
            if (!Notpossible) {
                rotations = Math.min(temp, rotations);
            }
        }
        return rotations == Integer.MAX_VALUE ? -1 : rotations ;
    }
    // topMap: 2 -> 4 , 1 -> 1 , 4 -> 1
    // bottomMap: 2 -> 3 , 3 -> 1 , 5 -> 1 , 6 -> 1
}
/*
private void print(Map<Integer, ?> map) {
        for (int num : map.keySet()) {
            System.out.print(num + " -> " + map.get(num));
            System.out.print(" ");
        }
        System.out.println();
    }  
 */