package February;

public class concatenation_of_consecutive_binary_numbers {
    static void main() {
        Solution solution = new Solution();
        System.out.println(solution.concatenatedBinary(3));
    }

    static class Solution {
        private static final int mod = 1_000_000_007;

        public int concatenatedBinary(int n) {
            long result = 0;
            int bits = 0;
            for (int num = 1; num <= n; num++) {
                if ((num & (num - 1)) == 0) bits++;
                result = ((result << bits) + num) % mod;
            }
            return (int) result;
        }
    }
}
