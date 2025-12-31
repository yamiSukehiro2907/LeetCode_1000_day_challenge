package June_16;

public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public int maximumDifference(int[] nums) {
            int left = nums[0];
            int ans = -1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] <= left) {
                    left = nums[i];
                } else {
                    ans = Math.max(ans, nums[i] - left);
                }
            }
            return ans;
        }
    }
}
