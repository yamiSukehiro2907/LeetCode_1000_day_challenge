package January;

public class max_square_side_length_with_sum_less_than_or_equal_threshold {
    static void main() {

    }

    static class Solution {
        public int maxSideLength(int[][] mat, int threshold) {
            int rows = mat.length, cols = mat[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int left = (j > 0) ? mat[i][j - 1] : 0;
                    int top = (i > 0) ? mat[i - 1][j] : 0;
                    int topLeft = (i > 0 && j > 0) ? mat[i - 1][j - 1] : 0;
                    mat[i][j] += (left + top - topLeft);
                }
            }
            int maxLength = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int length = maxLength + 1; i + 1 - length >= 0 && j + 1 - length >= 0; length++) {
                        int iPrev = i - length, jPrev = j - length;
                        int topLeft = (iPrev >= 0 && jPrev >= 0) ? mat[iPrev][jPrev] : 0;
                        int left = (jPrev >= 0) ? mat[i][jPrev] : 0;
                        int top = (iPrev >= 0) ? mat[iPrev][j] : 0;
                        int sum = mat[i][j] + topLeft - top - left;
                        if (sum <= threshold) maxLength = length;
                        else break;
                    }
                }
            }
            return maxLength;
        }
    }

    static class Solution2 {
        public int maxSideLength(int[][] grid, int threshold) {
            fillPrefixSum(grid);
            return findMax(grid, threshold);
        }

        private int findMax(int[][] grid, int threshold) {
            int length = Math.min(grid.length, grid[0].length);
            while (length > 0) {
                for (int rStart = 0; rStart <= grid.length - length; rStart++) {
                    int rEnd = rStart + length - 1;
                    if (rEnd >= grid.length)
                        break;
                    for (int cStart = 0; cStart <= grid[0].length; cStart++) {
                        int cEnd = cStart + length - 1;
                        if (cEnd >= grid[0].length)
                            break;
                        long sum = findSubMatrixSum(grid, rStart, cStart, rStart + length - 1, cStart + length - 1);
                        if (sum <= threshold)
                            return length;
                    }
                }
                length--;
            }
            return 0;
        }

        private void fillPrefixSum(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int left = (j > 0) ? grid[i][j - 1] : 0;
                    int top = (i > 0) ? grid[i - 1][j] : 0;
                    int topLeft = (i > 0 && j > 0) ? grid[i - 1][j - 1] : 0;
                    grid[i][j] += (left + top - topLeft);
                }
            }
        }

        private int findSubMatrixSum(int[][] grid, int rStart, int cStart, int rEnd, int cEnd) {
            int sum = grid[rEnd][cEnd];
            int top = (rStart > 0) ? grid[rStart - 1][cEnd] : 0;
            int left = (cStart > 0) ? grid[rEnd][cStart - 1] : 0;
            int topLeft = (rStart > 0 && cStart > 0) ? grid[rStart - 1][cStart - 1] : 0;
            return sum - top - left + topLeft;
        }
    }
}
