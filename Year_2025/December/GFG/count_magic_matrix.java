package December.GFG;

public class count_magic_matrix {
    static void main() {
        int[][] matrix = {{1, 2, 5}, {3, 4, 6}, {9, 8, 7}};
        Solution sol = new Solution();
        System.out.println(sol.numMagicSquaresInside(matrix));
    }

    static class Solution {
        private int visited;
        private static final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        public int numMagicSquaresInside(int[][] grid) {
            int count = 0;
            for (int i = 1; i < grid.length - 1; i++) {
                for (int j = 1; j < grid[i].length - 1; j++) {
                    if (!isValid(i, j, grid)) continue;
                    if (equal(grid[i - 1][j - 1] + grid[i][j] + grid[i + 1][j + 1], i, j, grid)) count++;
                }
            }
            return count;
        }

        private boolean equal(int target, int i, int j, int[][] grid) {
            int index = 0, sum = 0;
            while (index < 9) {
                sum += grid[directions[index][0] + i][directions[index][1] + j];
                index++;
                if (index % 3 == 0) {
                    if (sum != target) return false;
                    sum = 0;
                }
            }
            for (int col = 0; col < 3; col++) {
                sum = 0;
                for (int row = 0; row < 3; row++)
                    sum += grid[directions[col + row * 3][0] + i][directions[col + row * 3][1] + j];
                if (sum != target) return false;
            }
            sum = grid[i - 1][j + 1] + grid[i][j] + grid[i + 1][j - 1];
            return sum == target;
        }

        private boolean validNum(int num) {
            if (num < 1 || num > 9) return false;
            visited ^= (1 << num);
            return ((visited >> num) & 1) == 1;
        }

        private boolean isValid(int i, int j, int[][] grid) {
            this.visited = 0;
            for (int[] dir : directions) if (!validNum(grid[dir[0] + i][dir[1] + j])) return false;
            return true;
        }
    }
}
