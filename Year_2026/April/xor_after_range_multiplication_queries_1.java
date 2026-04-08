package April;

public class xor_after_range_multiplication_queries_1 {
    static void main() {

    }

    static class Solution {
        final static long MOD = 1_000_000_007;

        public int xorAfterQueries(int[] nums, int[][] queries) {
            long[] arr = new long[nums.length];
            for (int i = 0; i < nums.length; i++) arr[i] = nums[i];
            for (int[] query : queries) {
                for (int j = query[0]; j <= query[1]; j += query[2]) {
                    arr[j] = (arr[j] * (long) query[3]) % MOD;
                }
            }
            long xor = 0;
            for (long num : arr) xor ^= num;
            return (int) xor;
        }
    }
}
