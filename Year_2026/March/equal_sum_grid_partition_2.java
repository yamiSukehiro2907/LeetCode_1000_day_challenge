package March;

public class equal_sum_grid_partition_2 {
    static void main() {

    }

    static class Solution {
        private boolean checkSingleRow(int[][] grid) {
            long totalSum = 0;
            for (int j = 0; j < grid[0].length; j++) totalSum += grid[0][j];
            long currentSum = 0;
            for (int j = 0; j < grid[0].length - 1; j++) {
                currentSum += grid[0][j];
                long rightSum = totalSum - currentSum;
                if (rightSum == currentSum) return true;
                long diff = Math.abs(rightSum - currentSum);
                if (diff == grid[0][0] || diff == grid[0][j] || diff == grid[0][grid[0].length - 1] || diff == grid[0][j + 1])
                    return true;
            }
            return false;
        }

        private boolean checkSingleCol(int[][] grid) {
            long totalSum = 0;
            for (int i = 0; i < grid.length; i++) totalSum += grid[i][0];
            long currentSum = 0;
            for (int i = 0; i < grid.length - 1; i++) {
                currentSum += grid[i][0];
                long rightSum = totalSum - currentSum;
                if (rightSum == currentSum) return true;
                long diff = Math.abs(rightSum - currentSum);
                if (diff == grid[0][0] || diff == grid[i][0] || diff == grid[grid.length - 1][0] || diff == grid[i + 1][0])
                    return true;
            }
            return false;
        }

        public boolean canPartitionGrid(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if (m == 1) return checkSingleRow(grid);
            if (n == 1) return checkSingleCol(grid);
            long totalSum = 0;
            int maxNum = 0;
            for (int[] ints : grid) {
                for (int j = 0; j < n; j++) {
                    totalSum += ints[j];
                    if (maxNum < ints[j]) maxNum = ints[j];
                }
            }
            int[] freq = new int[maxNum + 1];
            for (int[] row : grid) for (int num : row) freq[num]++;
            long topSum = 0;
            int[] currentFreq = new int[maxNum + 1];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n; j++) {
                    currentFreq[grid[i][j]]++;
                    topSum += grid[i][j];
                }
                long bottomSum = totalSum - topSum;
                if (topSum == bottomSum) return true;
                long diff = Math.abs(topSum - bottomSum);
                int topWindowLength = i + 1;
                if (topSum > bottomSum && diff <= maxNum && currentFreq[(int) diff] > 0) {
                    if (topWindowLength > 1) return true;
                    if (diff == grid[0][0] || diff == grid[0][n - 1]) return true;
                }
                int bottomWindowLength = m - i - 1;
                if (topSum < bottomSum && diff <= maxNum && (freq[(int) diff] - currentFreq[(int) diff] > 0)) {
                    if (bottomWindowLength > 1) return true;
                    if (diff == grid[i + 1][0] || diff == grid[i + 1][n - 1]) return true;
                }
            }
            long leftSum = 0;
            currentFreq = new int[maxNum + 1];
            for (int j = 0; j < n - 1; j++) {
                for (int i = 0; i < m; i++) {
                    currentFreq[grid[i][j]]++;
                    leftSum += grid[i][j];
                }
                long rightSum = totalSum - leftSum;
                if (rightSum == leftSum) return true;
                long diff = Math.abs(leftSum - rightSum);
                int leftWindowLength = j + 1;
                if (leftSum > rightSum && diff <= maxNum && currentFreq[(int) diff] > 0) {
                    if (leftWindowLength > 1) return true;
                    if (grid[0][0] == diff || grid[m - 1][0] == diff) return true;
                }
                int rightWindowLength = n - j - 1;
                if (rightSum > leftSum && diff <= maxNum && freq[(int) diff] - currentFreq[(int) diff] > 0) {
                    if (rightWindowLength > 1) return true;
                    if (grid[0][j + 1] == diff || grid[m - 1][j + 1] == diff) return true;
                }
            }
            return false;
        }
    }
}
