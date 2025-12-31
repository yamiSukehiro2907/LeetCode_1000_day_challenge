
import java.util.*;

public class Solution {
    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            System.out.println(nextPrime(n));
        }
        
    }

    private static final List<Integer> primes = new ArrayList<>();

    static {
        init();
    }

    private static void init() {
        primes.add(2);
        primes.add(3);
        for (int i = 4; i < 504; i++) {
            if (prime(i)) {
                primes.add(i);
            }
        }
    }

    private static boolean prime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    public static int nextPrime(int target) {
        int start = 0;
        int end = primes.size() - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (primes.get(mid) > target) {
                ans = primes.get(mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

}
