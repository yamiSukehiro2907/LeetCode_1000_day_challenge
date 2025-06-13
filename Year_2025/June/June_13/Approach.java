import java.util.*;

public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public static int minimizeMax(int[] nums, int p) {
            Arrays.sort(nums);
            int start = 0, end = 0, n = nums.length;
            for (int i = 1; i < n; i++) {
                end = Math.max(end, nums[i] - nums[i - 1]);
            }
            while (start <= end) {
                int mid = start + (end - start >> 1);
                if (!possible(nums, mid, p))
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            return start;
        }

        private static boolean possible(int[] nums, int value, int pair) {
            int index = 1, n = nums.length;
            while (index < n && pair > 0) {
                if (nums[index] - nums[index - 1] <= value) {
                    index++;
                    pair--;
                }
                index++;
            }
            return pair == 0;
        }
    }
    /*
    class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int start = 0 , end = nums[n - 1] - nums[0];
        while(start < end){
            int mid = start + ((end - start)/2);
            if(countPairs(nums , mid) >= p) end = mid;
            else start = mid + 1;
        }
        return start;
    }
    private int countPairs(int[] nums , int value){
        int index = 0 , count = 0;
        while(index < nums.length - 1){
            if(nums[index + 1] - nums[index] <= value){
                index++;
                count++;
            }
            index++;
        }
        return count;
    }

}
     */
}
