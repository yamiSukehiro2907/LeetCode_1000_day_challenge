package February;

public class prime_number_of_set_bits_in_binary_representation {
    static void main() {
        int left = 6;
        int right = 10;
        Solution sol = new Solution();
        System.out.println(sol.countPrimeSetBits(left, right));
    }

    static class Solution {
        private static final boolean[] primeNumbers = new boolean[32];

        static {
            int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
            for (int num : primes) primeNumbers[num] = true;
        }

        public int countPrimeSetBits(int left, int right) {
            int count = 0;
            for (int num = left; num <= right; num++) if (primeNumbers[Integer.bitCount(num)]) count++;
            return count;
        }
    }
}
