package January;

import java.util.Arrays;

public class minimum_window_subsequence {
    static void main(String[] args) {
        String s1 = "fghhfjghijiihffi";
        String s2 = "fgfhhii";
        Solution s = new Solution();
        System.out.println(s.minWindow(s1, s2));
    }

    static class Solution {
        private static final int INF = (int) 1e9;
        private int[][] dp;
        private String s1, s2;
        private int s1Length, s2Length;

        public String minWindow(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
            this.s1Length = s1.length();
            this.s2Length = s2.length();
            if (s2Length == 0) return "";
            if (s1Length == 0 || s2Length > s1Length) return "";
            dp = new int[s1Length][s2Length];
            for (int[] row : dp) Arrays.fill(row, -1);
            int length = INF;
            int start = -1;
            for (int i = 0; i < s1Length; i++) {
                int len = solve(i, 0);
                if (len < length) {
                    length = len;
                    start = i;
                }
            }
            if (start == -1) return "";
            return s1.substring(start, start + length);
        }

        private int solve(int s1Index, int s2Index) {
            if (s2Index == s2Length) return 0;
            if (s1Index == s1Length) return INF;
            if (dp[s1Index][s2Index] != -1) return dp[s1Index][s2Index];
            int res = INF, take;
            if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
                take = solve(s1Index + 1, s2Index + 1);
                if (take < INF) take += 1;
                res = Math.min(res, take);
            }
            int skip = solve(s1Index + 1, s2Index);
            if (skip < INF) skip += 1;
            res = Math.min(res, skip);
            return dp[s1Index][s2Index] = res;
        }
    }
}
