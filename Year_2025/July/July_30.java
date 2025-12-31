public class July_30 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int longestSubarray(int[] nums) {
            int maxNum = nums[0];
            for (int num : nums) {
                if (num > maxNum) maxNum = num;
            }
            int ans = 1;
            int left = -1;
            int right = 0;
            while (right < nums.length) {
                if (nums[right] == maxNum) {
                    left = right;
                    while (right < nums.length && nums[right] == maxNum)
                        right++;
                    if (right >= nums.length) {
                        ans = Math.max(ans, nums.length - left);
                        return ans;
                    } else {
                        ans = Math.max(ans, right - left);
                    }
                } else {
                    right++;
                }
            }
            return ans;
        }
    }
}
