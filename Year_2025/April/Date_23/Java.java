package Year_2025.April.Date_23;

class Solution {
    static {
        for (int i = 0; i < 500; i++)
            countLargestGroup(1);
    }

    public static int countLargestGroup(int n) {
        if (n <= 9)
            return n;
        int map[] = new int[37];
        for (int i = 1; i <= n; i++) {
            int sum = getSum(i);
            map[sum]++;
        }
        int maxFreq = 0;
        int ans = 0;
        for (int count : map) {
            if (count > maxFreq) {
                ans = 1;
                maxFreq = count;
            } else if (count == maxFreq) {
                ans++;
            }
        }
        return ans;
    }

    private static int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}

public class Java {
    public static void main(String[] args) {
        System.out.println(Solution.countLargestGroup(174));
    }
}