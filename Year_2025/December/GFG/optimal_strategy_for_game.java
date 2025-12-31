package December.GFG;

import java.util.Arrays;

public class optimal_strategy_for_game {
    static void main() {
        int[] arr = {8, 15, 3, 7};
        Solution solution = new Solution();
        System.out.println(solution.maximumAmount(arr));
    }

    static class Solution {
        private int[][] dp;
        private int[] arr;

        public int maximumAmount(int[] a) {
            this.arr = a;
            int n = arr.length;
            this.dp = new int[n][n];
            for (int[] temp : dp) Arrays.fill(temp, -1);
            return find(0, n - 1);
        }

        private int find(int left, int right) {
            if (left > right) return 0;
            if (dp[left][right] != -1) return dp[left][right];
            int choice1 = arr[left] + Math.min(find(left + 2, right), find(left + 1, right - 1));
            int choice2 = arr[right] + Math.min(find(left + 1, right - 1), find(left, right - 2));
            return dp[left][right] = Math.max(choice1, choice2);
        }
    }

}
