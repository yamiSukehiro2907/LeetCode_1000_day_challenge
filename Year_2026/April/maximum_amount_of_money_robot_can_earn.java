package April;

import java.util.Arrays;

public class maximum_amount_of_money_robot_can_earn {
    static void main() {
        int[][] grid = {{-7, 12, 12, 13}, {-6, 19, 19, -6}, {9, -2, -10, 16}, {-4, 14, -10, -9}};
        Solution obj = new Solution();
        System.out.println(obj.maximumAmount(grid));
    }

    static class Solution {

        private Integer[][][] dp;
        private int[][] coins;
        private int rows, cols;

        public int maximumAmount(int[][] coins) {
            this.coins = coins;
            this.rows = coins.length;
            this.cols = coins[0].length;
            this.dp = new Integer[rows][cols][3];
            return find(0, 0, 2);
        }

        private int find(int row, int col, int skip) {
            if (row >= rows || col >= cols) return Integer.MIN_VALUE / 2;
            if (row == rows - 1 && col == cols - 1) return coins[row][col] < 0 && skip >= 1 ? 0 : coins[row][col];
            if (dp[row][col][skip] != null) return dp[row][col][skip];
            dp[row][col][skip] = coins[row][col] + Math.max(find(row + 1, col, skip), find(row, col + 1, skip));
            if (coins[row][col] < 0 && skip >= 1)
                dp[row][col][skip] = Math.max(dp[row][col][skip], Math.max(find(row + 1, col, skip - 1), find(row, col + 1, skip - 1)));
            return dp[row][col][skip];
        }
    }

    static class Solution2 {
        public int maximumAmount(int[][] coins) {
            int cols = coins[0].length;
            int[][] dp = new int[cols + 1][3];
            for (int[] row : dp) Arrays.fill(row, Integer.MIN_VALUE / 2);
            for (int i = 0; i < 3; i++) dp[1][i] = 0;
            for (int[] row : coins) {
                for (int j = 1; j <= cols; j++) {
                    int currCoin = row[j - 1];
                    dp[j][2] = Math.max(
                            // take the current coin and max from left and top
                            Math.max(dp[j][2], dp[j - 1][2]) + currCoin,
                            // don't take coin , take max from left and top (skip used by 1)
                            Math.max(dp[j - 1][1], dp[j][1])
                    );
                    dp[j][1] = Math.max(
                            Math.max(dp[j][1], dp[j - 1][1]) + currCoin,
                            Math.max(dp[j - 1][0], dp[j][0])
                    );
                    dp[j][0] = Math.max(dp[j - 1][0], dp[j][0]) + currCoin;
                }
            }
            return dp[cols][2];
        }
    }
}
