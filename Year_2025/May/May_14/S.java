package May.May_14;

import java.util.*;

class Solution {
    public static final int MOD = (int) 1e9 + 7;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        /*
         * observation:
         * since the only thing that matters is the length of the string after
         * transformations and
         * the transformations impact each individual character of the current s state
         * independently and the position/order
         * does not impact the transformation, only the letters, the order of s is
         * irrelevant, only need to pay attention to the counts of every letter.
         * 
         * can reduce s to a sz 26 vector counting freq of every letter in s.
         * Instead of transforming s, can transform vector counts.
         * 
         * have oldCnt and newCnt vectors for before and after transform
         * newCnt[i] = [sum over all k < 26 where nums[i-k] >= k (including wrap around
         * mod k) of oldCnt[i-k]]
         * 
         * such a transformation seems linear and seems like we can construct a matrix
         * to do through matrix mult
         * matrix mult is equiv to multiplying each col of matrix by old vector
         * cooresponding so can construct matrix by filling each
         * index's cooresponding value column with ones from row (i+1) % 26 to
         * (i+nums[i]) % 26
         * 
         * using such a matrix A can mult A*old = new
         * thus have A^t * old = new_t_transformed.
         * 
         * So problem is reduced to matrix exponentiation of A^t
         */
        int[] cnt = getLetterCounts(s);
        int[][] transformMatrix = createTransformMatrix(nums);

        int[] transformedCnt = matrixExp(transformMatrix, cnt, t);
        long res = 0;
        for (int freq : transformedCnt)
            res += freq;
        return (int) (res % MOD);

    }

    private int[] getLetterCounts(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++)
            cnt[s.charAt(i) - 'a']++;
        return cnt;
    }

    private int[][] createTransformMatrix(List<Integer> nums) {
        int[][] A = new int[26][26];
        for (int i = 0; i < 26; i++) {
            int lim = i + nums.get(i);
            for (int j = i + 1; j <= lim; j++) {
                A[j % 26][i] = 1;
            }
        }
        return A;
    }

    private int[] matrixVectorMult(int[][] A, int[] x) {
        int[] y = new int[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                y[i] = (int) ((y[i] + 1L * A[i][j] * x[j]) % MOD);
            }
        }
        return y;
    }

    private int[][] matrixSq(int[][] A) {
        int[][] C = new int[26][26];
        long[] acc = new long[26];

        for (int i = 0; i < 26; i++) {
            // 1) zero out our accumulator for row i
            Arrays.fill(acc, 0L);

            // 2) do the full A[i]·A multiplication into acc[j]
            for (int k = 0; k < 26; k++) {
                int aik = A[i][k];
                if (aik == 0)
                    continue; // skip zeros entirely

                int[] Ak = A[k];
                for (int j = 0; j < 26; j++) {
                    acc[j] += (long) aik * Ak[j];
                }

                // 3) every 4 k’s, flush acc[j] down to < MOD
                if ((k & 3) == 3) {
                    for (int j = 0; j < 26; j++) {
                        acc[j] %= MOD;
                    }
                }
            }

            // 4) final reduction & write into C
            for (int j = 0; j < 26; j++) {
                C[i][j] = (int) (acc[j] % MOD);
            }
        }

        return C;
    }

    private int[] matrixExp(int[][] matrix, int[] vector, int exp) {
        int[][] sq = copy(matrix);
        while (exp > 0) {
            if ((exp & 1) == 1)
                vector = matrixVectorMult(sq, vector);
            sq = matrixSq(sq);
            exp >>= 1;
        }
        return vector;
    }

    private int[][] copy(int[][] matrix) {
        int[][] c = new int[26][26];
        for (int i = 0; i < 26; i++)
            c[i] = matrix[i].clone();
        return c;
    }
}

public class S {
    public static void main(String[] args) {

    }
}
