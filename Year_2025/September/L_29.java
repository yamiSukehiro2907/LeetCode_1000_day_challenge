public class L_29 {
    public static void main(String[] args) {
        int[] arr = { 3, 7, 4, 5 };
        Solution solution = new Solution();
        System.out.println(solution.minScoreTriangulation(arr));
    }

    private static class Solution {
        private int[][] dp;

        public int minScoreTriangulation(int[] values) {
            int n = values.length;
            this.dp = new int[n][n];
            return solve(values, 0, n - 1);
        }

        private int solve(int[] num, int i, int j) {
            if (j - i < 2) return 0; // no possible triangle
            if (dp[i][j] != 0) return dp[i][j];
            int min = Integer.MAX_VALUE;
            for (int k = i + 1; k < j; k++) {
                // we chose a triangle then compute the possible minimum sum score of left and right
                min = Math.min(min, num[i] * num[j] * num[k] + solve(num, i, k) + solve(num, k, j));
            }
            dp[i][j] = min;
            return min;
        }
    }
}
