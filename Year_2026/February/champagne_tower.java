package February;

import java.util.Arrays;

public class champagne_tower {
    static void main() {
        int poured = 100000009;
        int query_row = 33;
        int query_col = 17;
        Solution solution = new Solution();
        System.out.println(solution.champagneTower(poured, query_row, query_col));
    }

    static class Solution {
        // below and this approach have same intuition but this one goes from right to left
        // thus using the same array to make changes
        // in each row the number of glasses increases by 1 so we start from the last glass to make decisions
        // each glass takes the champagne dropping from above glass
        // but glass next to that adds the dropping champagne
        public double champagneTower(int poured, int query_row, int query_glass) {
            double[] dp = new double[query_glass + 2];
            dp[0] = poured;
            for (int i = 0; i < query_row; i++) {
                for (int j = Math.min(i, query_glass); j >= 0; j--) {
                    if (dp[j] > 1) {
                        double toBePoured = (dp[j] - 1) / (double) 2;
                        dp[j] = toBePoured;
                        dp[j + 1] += toBePoured;
                    } else dp[j] = 0;
                }
            }
            return Math.min(1, dp[query_glass]);
        }
    }


    static class Solution2 {
        public double champagneTower(int poured, int query_row, int query_glass) {
            double[][] dp = new double[query_row + 1][];
            for (int i = 0; i <= query_row; i++) dp[i] = new double[i + 1];
            dp[0][0] = poured;
            for (int row = 1; row <= query_row; row++) {
                for (int index = 0; index < dp[row - 1].length; index++) {
                    if (dp[row - 1][index] <= 1) continue;
                    double toBePoured = (dp[row - 1][index] - 1) / (double) 2;
                    if (index + 1 < dp[row].length) dp[row][index + 1] += toBePoured;
                    dp[row][index] += toBePoured;
                }
            }
            for (double[] row : dp) System.out.println(Arrays.toString(row));
            return Math.min(1, dp[query_row][query_glass]);
        }
    }
}
