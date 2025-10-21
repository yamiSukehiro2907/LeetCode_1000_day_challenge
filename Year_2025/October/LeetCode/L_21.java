public class L_21 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public int maxFrequency(int[] nums, int k, int numOperations) {
            int max = 0, min = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num < min)
                    min = num;
                if (num > max)
                    max = num;
            }
            int[] freq = new int[max + 1];
            int[] prefix = new int[max + 1];
            for (int num : nums) freq[num]++;
            for (int i = min; i <= max; i++)
                prefix[i] = prefix[i - 1] + freq[i];
            int ans = 0;
            for (int i = min; i <= max; i++) {
                int low = 0;
                if (i - k - 1 > 0) low = prefix[i - k - 1];
                int high;
                if (i + k <= max) high = prefix[i + k];
                else high = prefix[max];
                int toChange = high - low - freq[i];
                ans = Math.max(ans, freq[i] + (toChange >= numOperations ? numOperations : toChange));
            }
            return ans;
        }
    }
    /*
    class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxFreq = 1, n = nums.length;
        Arrays.sort(nums);
        for (int i = nums[0] - k; i <= nums[n - 1] + k; i++) {
            int leftMost = find(nums, true, k, i), rightMost = find(nums, false, k, i), already = 0;
            int leftMostExactSame = findTarget(nums, true, i);
            if (leftMostExactSame != -1) {
                int rightMostExactSame = findTarget(nums, false, i);
                already = rightMostExactSame - leftMostExactSame + 1;
            }
            int inRange = rightMost - leftMost + 1;
            int operationsNeeded = inRange - already;
            if (operationsNeeded >= numOperations) operationsNeeded = numOperations;
            int possible = already + operationsNeeded;
            if (possible > maxFreq) maxFreq = possible;
        }
        return maxFreq;
    }

    private int find(int[] nums, boolean left, int k, int target) {
        int start = 0, end = nums.length - 1, ans = -1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (left) {
                if (nums[mid] + k >= target) {
                    ans = mid;
                    end = mid - 1;
                } else start = mid + 1;
            } else {
                if (nums[mid] - k <= target) {
                    ans = mid;
                    start = mid + 1;
                } else end = mid - 1;
            }
        }
        return ans;
    }

    private int findTarget(int[] nums, boolean left, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (nums[mid] == target) {
                if (left) while (mid - 1 >= 0 && nums[mid - 1] == target) mid--;
                else while (mid + 1 < nums.length && nums[mid + 1] == target) mid++;
                return mid;
            } else if (nums[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return -1;
    }
}
    */

}