package May.May_3;

// https://leetcode.com/problems/minimum-domino-rotations-for-equal-row
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int ans = -1;
        for(int i = 1 ; i <= 6 ; i++){
            int curr = solve(tops , bottoms , i);
            if(curr != -1 && (ans == -1 || curr < ans)) ans = curr; // if the rotation is possible and if not already calculated or curr_possible rotation is less than answer
        }
        return ans;
    }
    private int solve(int[] tops , int[] bottoms , int val){
        int topFlip = 0 , bottomFlip = 0;
        for(int i = 0 ; i < tops.length ; i++){
            if(tops[i] != val && bottoms[i] != val) return -1;
            else if(tops[i] == val && bottoms[i] == val) continue; // do not flip if both top and bottom are equal
            else if(tops[i] != val) bottomFlip++; // if top is not equal number then flip bottom
            else topFlip++; // if bottom is not equal to number then flip top
        }
        return Math.min(topFlip , bottomFlip); // minimum flips required to make top or bottom equal
    }
}
/*
Brute Approach : This works for all possible values of top and bottom
class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int topLength = tops.length, bottomLength = bottoms.length;
        if (topLength != bottomLength) return -1;
        if (topLength == 1) return 0;
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
            if (!Notpossible) rotations = Math.min(temp, rotations);
        }
        return rotations == Integer.MAX_VALUE ? -1 : rotations ;
    }
}
 */
public class Approach{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] tops = {};
        int[] bottoms = {};
        System.out.println(solution.minDominoRotations(tops, bottoms));
    }
}