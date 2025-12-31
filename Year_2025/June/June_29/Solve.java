public class Solve {
    public static void main(String[] args) {

    }

    static class Solution {
        public int numSubseq(int[] a, int t) {
            final int MOD = 1_000_000_007;
            Arrays.sort(a);
            int n = a.length;
            int[] p = new int[n];
            p[0] = 1;
            for (int i = 1; i < n; i++) {
                p[i] = (p[i - 1] * 2) % MOD;
            }
            int left = 0, right = n - 1, res = 0;
            while (left <= right) {
                if (a[left] + a[right] <= t) {
                    res = (res + p[right - left]) % MOD;
                    left++;
                } else {
                    right--;
                }
            }
            return res;
        }
    }
}
