package January;

public class minimize_maximum_pair_sum_array_one {
    static void main() {

    }

    static class Solution {
        private final int[] freq = new int[100001];

        public int minPairSum(int[] nums) {
            int max = nums[0];
            for (int num : nums) {
                if (num > max) max = num;
                freq[num]++;
            }
            int maxSum = 0;
            int start = 0, end = max;
            while (start < end) {
                while (start < end && freq[start] == 0) start++;
                while (start < end && freq[end] == 0) end--;
                maxSum = Math.max(maxSum, start + end);
                if (freq[end] > freq[start]) {
                    freq[end] -= freq[start];
                    freq[start] = 0;
                    start++;
                } else if (freq[start] > freq[end]) {
                    freq[start] -= freq[end];
                    freq[end] = 0;
                    end--;
                } else {
                    freq[start++] = 0;
                    freq[end--] = 0;
                }
            }
            return maxSum;
        }
    }
}
