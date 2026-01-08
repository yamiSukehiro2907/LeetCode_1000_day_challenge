package January;

import java.util.Arrays;

public class max_dot_product_of_two_subsequences {
    static void main() {
        int[] nums1 = {2, 1, -2, 5};
        int[] nums2 = {3, 0, -6};
        Solution s = new Solution();
        System.out.println(s.maxDotProduct(nums1, nums2));
    }

    static class Solution {
        private int[] nums1, nums2;
        private Integer[][] dp;

        public int maxDotProduct(int[] nums1, int[] nums2) {
            // return maxDotProduct1(nums1, nums2);
            return maxDotProduct2(nums1, nums2);
        }

        public int maxDotProduct2(int[] nums1, int[] nums2) {
            int n1Length = nums1.length, n2Length = nums2.length;
            int[][] dp = new int[n1Length + 1][n2Length + 1];
            for (int[] row : dp) Arrays.fill(row, Integer.MIN_VALUE / 2);
            for (int i = 1; i <= n1Length; i++) {
                for (int j = 1; j <= n2Length; j++) {
                    int currentPair = nums1[i - 1] * nums2[j - 1];
                    int previous = dp[i - 1][j - 1];
                    if (previous > 0) currentPair += previous;
                    int skipN1 = dp[i - 1][j];
                    int skipN2 = dp[i][j - 1];
                    dp[i][j] = Math.max(currentPair, Math.max(skipN1, skipN2));
                }
            }
            return dp[n1Length][n2Length];
        }

        public int maxDotProduct1(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            dp = new Integer[nums1.length][nums2.length];
            return solve(0, 0);
        }

        private int solve(int n1Index, int n2Index) {
            if (n1Index >= nums1.length || n2Index >= nums2.length) return Integer.MIN_VALUE;
            if (dp[n1Index][n2Index] != null) return dp[n1Index][n2Index];
            int include = nums1[n1Index] * nums2[n2Index];
            int next = solve(n1Index + 1, n2Index + 1);
            if (next > 0) include += next;
            int skipN1 = solve(n1Index + 1, n2Index);
            int skipN2 = solve(n1Index, n2Index + 1);
            return dp[n1Index][n2Index] = Math.max(include, Math.max(skipN1, skipN2));
        }
    }
}
