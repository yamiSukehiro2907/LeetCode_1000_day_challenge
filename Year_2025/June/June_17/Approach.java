public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public int countGoodArrays(int n, int m, int k) {
            // Pehle factorial ka base case set karte hain
            if (fact[0] == 0)
                fact[0] = 1;

            // Formula: m * (m-1)^(n-1-k) * C(n-1, n-1-k)
            // Yahan m = first element ke liye choices
            // (m-1)^(n-1-k) = baaki positions ke liye choices (adjacent same nahi hone
            // chahiye)
            // C(n-1, n-1-k) = kitne positions mein same elements rakh sakte hain
            long result = m * pow(m - 1, n - 1 - k) % mod * comb(n - 1, n - 1 - k) % mod;
            return (int) result;
        }

        // Modulo value - large prime number hai taaki overflow na ho
        int mod = 1_000_000_007;

        // Static arrays - ek baar calculate karke reuse karte hain
        static long[] invs = new long[100001]; // Inverse values store karne ke liye
        static int[] fact = new int[100001]; // Factorial values store karne ke liye

        // Fast exponentiation - binary exponentiation use karte hain
        // Time complexity: O(log exp)
        public long pow(int base, int exp) {
            long result = 1;
            long b = base;
            while (exp > 0) {
                // Agar exp odd hai toh result mein multiply kar do
                if ((exp & 1) == 1)
                    result = result * b % mod;
                // Base ko square karo aur exponent ko half karo
                b = b * b % mod;
                exp /= 2;
            }
            return result;
        }

        // Combination calculate karna - nCr = n! / (r! * (n-r)!)
        // Modular arithmetic use karte hain division ke liye
        public long comb(int n, int r) {
            return (long) factorial(n) * inverse(factorial(n - r)) % mod * inverse(factorial(r)) % mod;
        }

        // Factorial calculate karna with memoization
        // Agar pehle se calculated hai toh wahi return kar do
        public long factorial(int n) {
            if (fact[n] != 0)
                return fact[n];
            // Recursive call - fact[n] = n * fact[n-1]
            return fact[n] = (int) (factorial(n - 1) * n % mod);
        }

        // Modular inverse nikalna - Fermat's little theorem use kar sakte the
        // Lekin yahan extended Euclidean algorithm ka approach hai
        // Formula: inverse(x) = (mod - (mod/x) * inverse(mod%x)) % mod
        public long inverse(long x) {
            if (x == 1)
                return x;
            return mod - mod / x * inverse(mod % x) % mod;
        }
    }
}