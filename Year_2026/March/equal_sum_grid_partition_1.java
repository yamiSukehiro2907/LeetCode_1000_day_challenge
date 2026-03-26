package March;

public class equal_sum_grid_partition_1 {
    static void main() {

    }

    static class Solution {
        public boolean canPartitionGrid(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int index = 0;
            long totalSum = 0;
            long[] rowSum = new long[m];
            long[] colSum = new long[n];
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < m; i++) {
                    colSum[j] += grid[i][j];
                    rowSum[i] += grid[i][j];
                }
                totalSum += colSum[j];
            }
            if (totalSum % 2 != 0) return false;
            long currentSum1 = totalSum / 2;
            long currentSum2 = totalSum / 2;
            for (int j = 0; j < Math.max(m, n); j++) {
                if (j < m) {
                    currentSum1 -= rowSum[j];
                    rowSum[j] = 0;
                    if (currentSum1 == 0) return true;
                }
                if (j < n) {
                    currentSum2 -= colSum[j];
                    colSum[j] = 0;
                    if (currentSum2 == 0) return true;
                }
                if (currentSum1 < 0 && currentSum2 < 0) return false;
            }
            return false;
        }
    }
}
