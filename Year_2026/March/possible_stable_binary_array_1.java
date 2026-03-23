package March;

import java.util.Arrays;

public class possible_stable_binary_array_1 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int zero = 3, one = 3, limit = 2;
        System.out.println(sol.numberOfStableArrays(zero, one, limit));
    }

    static class Solution {
        public int numberOfStableArrays(int zero, int one, int limit) {
            final int mod = 1_000_000_007;
            int L = limit + 1;
            int[][] dpEndingWithZero = new int[zero + 1][one + 1];
            int[][] dpEndingWithOne = new int[zero + 1][one + 1];
            for (int i = 1; i <= Math.min(zero, limit); ++i) dpEndingWithZero[i][0] = 1;
            for (int j = 1; j <= Math.min(one, limit); ++j) dpEndingWithOne[0][j] = 1;
            for (int i = 1; i <= zero; ++i) {
                for (int j = 1; j <= one; ++j) {
                    dpEndingWithZero[i][j] = (dpEndingWithZero[i - 1][j] + dpEndingWithOne[i - 1][j] - (i >= L ? dpEndingWithOne[i - L][j] : 0)) % mod;
                    dpEndingWithOne[i][j] = (dpEndingWithOne[i][j - 1] + dpEndingWithZero[i][j - 1] - (j >= L ? dpEndingWithZero[i][j - L] : 0)) % mod;
                    dpEndingWithZero[i][j] = (dpEndingWithZero[i][j] + mod) % mod;
                    dpEndingWithOne[i][j] = (dpEndingWithOne[i][j] + mod) % mod;
                }
            }
            return (dpEndingWithZero[zero][one] + dpEndingWithOne[zero][one]) % mod;
        }
    }
}

