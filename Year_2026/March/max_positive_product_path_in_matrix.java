package March;

public class max_positive_product_path_in_matrix {
    static void main() {

    }

    static class Solution {
        private Cell[][] dp;
        private int m, n;
        private int[][] grid;

        public int maxProductPath(int[][] grid) {
            this.m = grid.length;
            this.n = grid[0].length;
            this.grid = grid;
            this.dp = new Cell[m][n];
            Cell ans = find(0, 0);
            if (ans.max < 0) return -1;
            return (int) (ans.max % 1_000_000_007L);
        }

        private Cell find(int i, int j) {
            if (i == m - 1 && j == n - 1) return new Cell(grid[i][j], grid[i][j]);
            int INF = Integer.MAX_VALUE;
            if (i >= m || j >= n) return new Cell(INF, INF);
            if (dp[i][j] != null) return dp[i][j];
            Cell right = find(i, j + 1);
            Cell bottom = find(i + 1, j);
            Cell currentCell = new Cell(0, 0);
            if (right.min != INF && bottom.min != INF) {
                long rightMax = (long) grid[i][j] * right.max;
                long rightMin = (long) grid[i][j] * right.min;
                long bottomMax = (long) grid[i][j] * bottom.max;
                long bottomMin = (long) grid[i][j] * bottom.min;
                currentCell.min = Math.min(Math.min(rightMax, rightMin), Math.min(bottomMax, bottomMin));
                currentCell.max = Math.max(Math.max(rightMax, rightMin), Math.max(bottomMax, bottomMin));
            } else if (bottom.min == INF) {
                currentCell.min = grid[i][j] * right.min;
                currentCell.max = grid[i][j] * right.max;
            } else {
                currentCell.min = grid[i][j] * bottom.min;
                currentCell.max = grid[i][j] * bottom.max;
            }
            dp[i][j] = currentCell;
            return currentCell;
        }

        static class Cell {
            long min;
            long max;

            Cell(long min, long max) {
                this.min = min;
                this.max = max;
            }
        }
    }
}
