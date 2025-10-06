public class L_6 {
    public static void main(String[] args) {

    }

    private static class Solution {
        private static final int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        private int n;
        private int[][] grid;

        static {
            System.gc();
            Solution solution = new Solution();
            for (int i = 0; i < 100; i++) {
                solution.swimInWater(new int[1][1]);
            }
        }

        public int swimInWater(int[][] g) {
            if (g.length == 1)
                return g[0][0];
            this.grid = g;
            this.n = grid.length;
            int start = Math.max(grid[0][0], grid[n - 1][n - 1]);
            int end = n * n - 1;
            int mid;
            int ans = 0;
            while (start <= end) {
                mid = (start + end) / 2;
                boolean[] visited = new boolean[n * n];
                if (dfs(0, 0, mid, visited)) {
                    ans = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return ans;
        }

        private boolean dfs(int i, int j, int mid, boolean[] visited) {
            int index = i * n + j;
            if (visited[index])
                return true;
            visited[index] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + dir[k][0];
                int newY = j + dir[k][1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n
                        && !visited[newX * n + newY] && grid[newX][newY] <= mid) {
                    if (newX == n - 1 && newY == n - 1) {
                        return true;
                    }
                    if (dfs(newX, newY, mid, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    /*
    class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        queue.add(new Pair(0, 0, grid[0][0]));
        int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Pair temp = queue.poll();
            if (temp.x == n - 1 && temp.y == n - 1) return temp.distance;
            visited[temp.x][temp.y] = true;
            for (int[] dir : directions) {
                int newX = temp.x + dir[0];
                int newY = temp.y + dir[1];
                if (newX >= 0 && newY >= 0 && newX < n && newY < n  && !visited[newX][newY]) {
                    if(grid[newX][newY] - temp.distance < 0) queue.add(new Pair(newX , newY , temp.distance));
                    else queue.add(new Pair(newX , newY , grid[newX][newY] - temp.distance + temp.distance));
                }
            }
        }
        return -1;
    }

    private class Pair {
        int x;
        int y;
        int distance;

        public Pair(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
     */
}
