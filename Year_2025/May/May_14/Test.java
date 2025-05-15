import java.util.*;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcyy";
        int t = 2;
        int nums[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2 };
        System.out.println(nums.length);
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        System.out.println("Answer:- " + solution.lengthAfterTransformations(s, t, numsList));
    }

    static class Solution {
        private static final int MOD = (int) 1e9 + 7;

        public int lengthAfterTransformations(String str, int transformations, List<Integer> nums) {
            int n = nums.size();
            if (n != 26) {
                return -1;
            }
            // In this we are finding the transformation of each character in which it can
            // transform
            long dp[][] = new long[26][26];
            for (int index = 0; index < n; index++) {
                // suppose a has 1
                for (int place = 0; place < nums.get(index); place++) {
                    dp[index][(index + 1 + place) % 26]++;
                }
                // dp will be filled by b (as a is transform upto 1 place)
            }

            // matrix power DP ^ t
            dp = powerMatrix(dp, transformations);

            // Frequency of each character
            long[][] freq = new long[1][26];
            for (char ch : str.toCharArray()) {
                freq[0][ch - 'a']++;
            }

            // freq = freq * dp
            freq = multiply(dp, freq);

            long total = 0;
            for (long cnt : freq[0]) {
                total = (total + cnt) % MOD;
            }
            return 0;
        }

        private long[][] multiply(long[][] m1, long[][] m2) {
            long[][] result = new long[m1.length][m2[0].length];
            for (int i = 0; i < m1.length; i++) {
                for (int j = 0; j < m2[0].length; j++) {
                    for (int k = 0; k < m2.length; k++) {
                        result[i][j] = (result[i][j] + (m1[i][k] * m2[k][j]) % MOD) % MOD;
                    }
                }
            }
            return result;
        }

        private long[][] powerMatrix(long[][] matrix, long exponent) {
            int n = matrix.length;
            long[][] result = new long[n][n];
            for (int i = 0; i < n; i++)
                result[i][i] = 1;
            while (exponent > 0) {
                if ((exponent & 1) == 1)
                    result = multiply(result, matrix);
                matrix = multiply(matrix, matrix);
                exponent >>= 1;
            }
            return result;
        }
    }
}
