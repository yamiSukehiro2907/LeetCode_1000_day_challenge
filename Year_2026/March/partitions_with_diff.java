package March;

import java.util.Arrays;

public class partitions_with_diff {
    static void main() {
        int[] arr = {5, 2, 6, 4};
        Solution obj = new Solution();
        System.out.println(obj.countPartitions(arr, 3));
    }

    static class Solution {
        private int[][] dp;
        private int totalSum;
        private int diff;

        public int countPartitions(int[] arr, int diff) {
            this.totalSum = 0;
            this.diff = diff;
            for (int num : arr) this.totalSum += num;
            this.dp = new int[totalSum + 1][arr.length + 1];
            for (int[] row : this.dp) Arrays.fill(row, -1);
            return diff != 0 ? countWays(0, 0, arr) / 2 : countWays(0, 0, arr);
        }

        private int countWays(int currentSum, int index, int[] arr) {
            if (index >= arr.length) return Math.abs(totalSum - 2 * currentSum) == diff ? 1 : 0;
            if (dp[currentSum][index] != -1) return dp[currentSum][index];
            int totalWays = 0;
            totalWays += countWays(currentSum, index + 1, arr);
            totalWays += countWays(currentSum + arr[index], index + 1, arr);
            return dp[currentSum][index] = totalWays;
        }
    }

}
