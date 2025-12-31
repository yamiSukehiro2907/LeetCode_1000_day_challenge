import java.util.*;

public class July_8 {
    public static void main(String[] args) {

    }

    static class Solution {
        private int[][] memo;

        public int maxValue(int[][] events, int k) {
            Arrays.sort(events, (a, b) -> a[0] - b[0]);
            int n = events.length;
            this.memo = new int[k + 1][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return solve(events, k, 0);
        }

        private int solve(int[][] events, int k, int index) {
            if (k == 0 || index >= events.length)
                return 0;
            if (memo[k][index] != -1)
                return memo[k][index];
            int nextIndex = find(events, events[index][1]);
            memo[k][index] = Math.max(events[index][2] + solve(events, k - 1, nextIndex), solve(events, k, index + 1));
            return memo[k][index];
        }

        private int find(int[][] events, int target) {
            int start = 0, end = events.length;
            while (start < end) {
                int mid = start + ((end - start) / 2);
                if (events[mid][0] <= target)
                    start = mid + 1;
                else
                    end = mid;
            }
            return start;
        }
    }
}
