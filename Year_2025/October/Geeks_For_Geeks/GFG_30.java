
public class GFG_30 {

    public static void main(String[] args) {

        Solution solution = new Solution();
        char[][] grid = {{}};
        solution.fill(grid);
    }

    private static class Solution {

        public void fill(char[][] grid) {
            this.rows = grid.length;
            this.cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                if (!visited[i][0] && grid[i][0] == 'O') {
                    dfs(i, 0, visited, grid);
                }
                if (!visited[i][cols - 1] && grid[i][cols - 1] == 'O') {
                    dfs(i, cols - 1, visited, grid);
                }
            }
            for (int j = 0; j < cols; j++) {
                if (!visited[0][j] && grid[0][j] == 'O') {
                    dfs(0, j, visited, grid);
                }
                if (!visited[rows - 1][j] && grid[rows - 1][j] == 'O') {
                    dfs(rows - 1, j, visited, grid);
                }
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (!visited[i][j]) {
                        grid[i][j] = 'X';
                    } else {
                        grid[i][j] = 'O';
                    }
                }
            }
        }
        private int rows;
        private int cols;
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private void dfs(int x, int y, boolean[][] visited, char[][] grid) {
            if (visited[x][y]) {
                return;
            }
            visited[x][y] = true;
            for (int[] dir : directions) {
                int newX = dir[0] + x;
                int newY = dir[1] + y;
                if (newX < rows && newX >= 0 && newY < cols && newY >= 0 && grid[newX][newY] == 'O') {
                    dfs(newX, newY, visited, grid);
                }
            }
        }

    }
}
