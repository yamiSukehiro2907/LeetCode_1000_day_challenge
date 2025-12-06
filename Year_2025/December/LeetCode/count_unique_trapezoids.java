package December.LeetCode;
import java.util.HashMap;
import java.util.Map;

public class count_unique_trapezoids {
    public static void main(String[] args) {

    }
    static class Solution {
        private static final int MOD = 1000000007;
        public int countTrapezoids(int[][] points) {
            Map<Integer, Integer> freq = new HashMap<>();
            for(int[] point : points) freq.merge(point[1], 1, Integer::sum);
            long sum = 0, ans = 0;
            for(int x : freq.values()) {
                long val = x * (x - 1L) / 2L;
                /// nC2 of current point
                ans += val * sum;
                /// pairs with previous number of unique trapezoids
                sum += val;
                /// add the current trapezoids this y can form to total trapezoids till now
            }
            return (int) (ans % MOD);
        }
    }
}
