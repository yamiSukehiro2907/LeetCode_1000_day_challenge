package April.Date_28;

public class finalSol {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = { 1, 1, 1 };
        System.out.println(solution.countSubarrays(arr, (long) (5)));
    }

    static class Solution {
        public long countSubarrays(int[] nums, long k) {
            long total = 0, currsum = 0;
            int left = 0;
            int n = nums.length;
            int right = 0;
            for (right = 0; right < n; right++) {
                currsum += nums[right];
                while (getScore(left, right, currsum) >= k) {
                    currsum -= nums[left];
                    left++;
                }
                total += (right - left + 1);
            }
            return total;
        }

        private long getScore(int left, int right, long currsum) {
            return ((long) (right + 1 - left) * (currsum));
        }
    }
}
