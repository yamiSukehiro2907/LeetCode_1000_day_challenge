package June_28;

import java.util.Arrays;

public class Solve {
    static class Solution {
        public int[] maxSubsequence(int[] nums, int k) {
            int n = nums.length;
            int[] sorted = Arrays.copyOf(nums, n);
            Arrays.sort(sorted);
            int threshold = sorted[n - k];
            int thresholdCnt = 0;
            for (int i = n - k; i < n; i++) {
                if (sorted[i] == threshold) {
                    thresholdCnt++;
                }
            }
            int[] ans = new int[k];
            int p = 0;
            for (int num : nums) {
                if (num > threshold) {
                    ans[p++] = num;
                } else if (num == threshold && thresholdCnt > 0) {
                    ans[p++] = num;
                    thresholdCnt--;
                }
                if (p == k) {
                    break;
                }
            }
            return ans;
        }
    }
    /*
    
     */

    public static void main(String[] args) {
        class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        if(nums.length <= 1) return nums;
        Pair[] pairs = new Pair[nums.length];
        for(int i = 0 ; i < nums.length ; i++) pairs[i] = new Pair(nums[i] , i);
        Arrays.sort(pairs , (a , b) -> a.num - b.num);
        Pair[] fin = Arrays.copyOfRange(pairs , nums.length - k , nums.length);
        Arrays.sort(fin , (a , b) -> a.index - b.index);
        int[] result = new int[fin.length];
        for(int i = 0 ; i < fin.length ; i++) result[i] = fin[i].num;
        return result;
    }
    private class Pair{
        int num;
        int index;

        public Pair(int num , int index){
            this.num = num;
            this.index = index;
        }
    }
}
    }
}
