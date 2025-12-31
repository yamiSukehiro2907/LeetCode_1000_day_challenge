package December.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class count_square_sum_triplets {
    public static void main(String[] args) {
        print(5);
        print2(25);
    }

    private static void print(int n) {
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                int a = i * i, b = j * j;
                int c = a + b;
                int k = (int) Math.sqrt(c);
                if (k > n) break;
                if (k * k == c && k > j) {
                    System.out.println(i + " " + j + " " + k);
                    numbers.add(i + "," + j + "," + k);
                }
            }
        }
        numbers.sort((a, b) -> {
            String[] s1 = a.split(",");
            String[] s2 = b.split(",");
            for (int i = 0; i < s1.length; i++) {
                int n1 = Integer.parseInt(s1[i]);
                int n2 = Integer.parseInt(s2[i]);
                if (n1 != n2) {
                    return n1 - n2;
                }
            }
            return 0;
        });

        System.out.println(numbers);
    }

    private static void print2(int k) {
        int ans = 0;
        for (int m = 3; m * m < k * 2; m += 2) {
            for (int n = 1; n < m && m * m + n * n <= k * 2; n += 2) {
                if (gcd(m, n) == 1) {
                    int ans1 = k * 2 / (m * m + n * n);
                    ans += ans1;
                }
            }
        }
    }

    private static int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }
}
