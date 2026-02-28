package February;

public class number_of_submatrices_that_sum_to_target {
    static void main() {
        int[][] matrix = {
                {2, 4, 7, 8, 10},
                {3, 1, 1, 1, 1},
                {9, 11, 1, 2, 1},
                {12, -17, 1, 1, 1},
        };
        int target = 10;
        System.out.println(new Solution().countSquare(matrix, target));
    }

    static class Solution {
        public int countSquare(int[][] mat, int x) {
            int rows = mat.length, cols = mat[0].length;
            long[][] prefix = new long[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    prefix[i][j] = mat[i][j];
                    if (j > 0) prefix[i][j] += prefix[i][j - 1];
                    if (i > 0) prefix[i][j] += prefix[i - 1][j];
                    if (i > 0 && j > 0) prefix[i][j] -= prefix[i - 1][j - 1];
                }
            }
            int count = 0;
            int maxSide = Math.min(rows, cols);
            for (int side = 0; side < maxSide; side++) {
                for (int row = 0; row + side < rows; row++) {
                    for (int col = 0; col + side < cols; col++) {
                        long area = prefix[row + side][col + side]
                                - (row > 0 ? prefix[row - 1][col + side] : 0L)
                                - (col > 0 ? prefix[row + side][col - 1] : 0L)
                                + ((row > 0 && col > 0) ? prefix[row - 1][col - 1] : 0L);
                        if (area == (long) x) count++;
                    }
                }
            }
            return count;
        }
    }
}

