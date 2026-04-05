package April;

import java.util.Arrays;

public class target_sum {
    static void main() {
        Solution s = new Solution();
        int[] arr = {1, 1, 1, 1, 1};
        System.out.println(s.totalWays(arr, 3));
    }

    static class Solution {

        private int[][] dp;
        private int totalSum;
        private int[] arr;
        private int target;

        public int totalWays(int[] arr, int target) {
            this.arr = arr;
            this.totalSum = 0;
            for (int num : arr) totalSum += num;
            this.target = target;
            this.dp = new int[arr.length][2 * totalSum + 1];
            for (int[] row : dp) Arrays.fill(row, -1);
            return find(0, 0);
        }

        private int find(int index, int currentSum) {
            if (index == arr.length) return currentSum == target ? 1 : 0;
            int sumIndex = currentSum + totalSum;
            if (dp[index][sumIndex] != -1) return dp[index][sumIndex];
            int ways = find(index + 1, currentSum - arr[index]);
            ways += find(index + 1, currentSum + arr[index]);
            return dp[index][sumIndex] = ways;
        }
    }
}
