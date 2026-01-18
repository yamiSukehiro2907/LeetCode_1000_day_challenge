package January;

public class largest_magic_square {
    static void main() {

    }

    static class Solution {
        private int[][] grid;
        private int rows;
        private int cols;
        private int[][] rowPrefix;
        private int[][] colPrefix;

        public int largestMagicSquare(int[][] grid) {
            this.grid = grid;
            this.rows = grid.length;
            this.cols = grid[0].length;
            this.rowPrefix = new int[rows][cols + 1];
            this.colPrefix = new int[rows + 1][cols];
            fillPrefixSums();
            return findMax();
        }

        private void fillPrefixSums() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                    colPrefix[i + 1][j] = colPrefix[i][j] + grid[i][j];
                }
            }
        }

        private int findMax() {
            for (int length = Math.min(rows, cols); length > 1; length--) {
                for (int r = 0; r <= rows - length; r++) {
                    for (int c = 0; c <= cols - length; c++) if (isValid(r, c, length)) return length;
                }
            }
            return 1;
        }

        private boolean isValid(int r, int c, int length) {
            int targetSum = rowPrefix[r][c + length] - rowPrefix[r][c];
            for (int i = 0; i < length; i++) {
                int currentInfoRow = rowPrefix[r + i][c + length] - rowPrefix[r + i][c];
                int currentInfoCol = colPrefix[r + length][c + i] - colPrefix[r][c + i];
                if (currentInfoRow != targetSum || currentInfoCol != targetSum) return false;
            }
            return validDiagonalSum(r, c, length, targetSum);
        }

        private boolean validDiagonalSum(int r, int c, int length, int targetSum) {
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < length; i++) {
                sum1 += grid[r + i][c + i];
                sum2 += grid[r + i][c + length - 1 - i];
            }
            return sum1 == targetSum && sum2 == targetSum;
        }
    }
}
