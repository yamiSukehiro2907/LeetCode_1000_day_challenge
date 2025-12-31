import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class August_27 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = { { 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 0 }, { 2, 0, 0, 0, 0 }, { 0, 0, 2, 2, 2 }, { 2, 0, 0, 2, 0 } };
        System.out.println(solution.lenOfVDiagonal(arr));
    }

    static class Solution {
        private static final Map<String, int[]> DIRECTIONS = Map.of(
                "TL", new int[] { -1, -1 },
                "TR", new int[] { -1, 1 },
                "BL", new int[] { 1, -1 },
                "BR", new int[] { 1, 1 });

        private int[][] grid;

        private int nextNum(int num) {
            return num == 2 ? 0 : 2;
        }

        private int solve(int row, int col, String dir, boolean turned, int num) {
            int distance = 0, currnum = grid[row][col];
            int r = row, c = col;

            if (turned) {
                int[] direction = DIRECTIONS.get(dir);
                distance++;
                r += direction[0];
                c += direction[1];
                while (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length &&
                        nextNum(currnum) == grid[r][c]) {
                    distance++;
                    currnum = grid[r][c];
                    r += direction[0];
                    c += direction[1];
                }
                return distance;
            }

            int d1 = 0, d2 = 0;
            if (dir.equals("TR")) {
                if (r - 1 >= 0 && c + 1 < grid[0].length && grid[r - 1][c + 1] == nextNum(num)) {
                    d1 = solve(r - 1, c + 1, "TR", false, grid[r - 1][c + 1]);
                }
                if (r + 1 < grid.length && c + 1 < grid[0].length && grid[r + 1][c + 1] == nextNum(num)) {
                    d2 = solve(r + 1, c + 1, "BR", true, grid[r + 1][c + 1]);
                }
            } else if (dir.equals("TL")) {
                if (r - 1 >= 0 && c + 1 < grid[0].length && grid[r - 1][c + 1] == nextNum(num)) {
                    d1 = solve(r - 1, c + 1, "TR", true, grid[r - 1][c + 1]);
                }
                if (r - 1 >= 0 && c - 1 >= 0 && grid[r - 1][c - 1] == nextNum(num)) {
                    d2 = solve(r - 1, c - 1, "TL", false, grid[r - 1][c - 1]);
                }
            } else if (dir.equals("BR")) {
                if (r + 1 < grid.length && c + 1 < grid[0].length && grid[r + 1][c + 1] == nextNum(num)) {
                    d1 = solve(r + 1, c + 1, "BR", false, grid[r + 1][c + 1]);
                }
                if (r + 1 < grid.length && c - 1 >= 0 && grid[r + 1][c - 1] == nextNum(num)) {
                    d2 = solve(r + 1, c - 1, "BL", true, grid[r + 1][c - 1]);
                }
            } else {
                if (r - 1 >= 0 && c - 1 >= 0 && grid[r - 1][c - 1] == nextNum(num)) {
                    d1 = solve(r - 1, c - 1, "TL", true, grid[r - 1][c - 1]);
                }
                if (r + 1 < grid.length && c - 1 >= 0 && grid[r + 1][c - 1] == nextNum(num)) {
                    d2 = solve(r + 1, c - 1, "BL", false, grid[r + 1][c - 1]);
                }
            }
            return 1 + Math.max(d1, d2);
        }

        private class DiagonalTask implements Callable<Integer> {
            private final List<int[]> positions;

            public DiagonalTask(List<int[]> positions) {
                this.positions = positions;
            }

            @Override
            public Integer call() {
                int maxLength = 0;

                for (int[] pos : positions) {
                    int i = pos[0];
                    int j = pos[1];

                    int localMax = 1;

                    if (i + 1 < grid.length && j + 1 < grid[0].length && grid[i + 1][j + 1] == 2) {
                        int d = solve(i + 1, j + 1, "BR", false, 2) + 1;
                        localMax = Math.max(localMax, d);
                    }
                    if (i + 1 < grid.length && j - 1 >= 0 && grid[i + 1][j - 1] == 2) {
                        int d = solve(i + 1, j - 1, "BL", false, 2) + 1;
                        localMax = Math.max(localMax, d);
                    }
                    if (i - 1 >= 0 && j + 1 < grid[0].length && grid[i - 1][j + 1] == 2) {
                        int d = solve(i - 1, j + 1, "TR", false, 2) + 1;
                        localMax = Math.max(localMax, d);
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1] == 2) {
                        int d = solve(i - 1, j - 1, "TL", false, 2) + 1;
                        localMax = Math.max(localMax, d);
                    }

                    maxLength = Math.max(maxLength, localMax);
                }

                return maxLength;
            }
        }

        public int lenOfVDiagonal(int[][] grid) {
            this.grid = grid;
            int m = grid.length, n = grid[0].length;

            List<int[]> onesPositions = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        onesPositions.add(new int[] { i, j });
                    }
                }
            }

            if (onesPositions.isEmpty()) {
                return 0;
            }

            if (onesPositions.size() <= 10) {
                return new DiagonalTask(onesPositions).call();
            }

            int numThreads = Math.min(Runtime.getRuntime().availableProcessors(),
                    (onesPositions.size() + 9) / 10);
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);

            try {
                List<Future<Integer>> futures = new ArrayList<>();
                int batchSize = (onesPositions.size() + numThreads - 1) / numThreads;

                for (int i = 0; i < onesPositions.size(); i += batchSize) {
                    int end = Math.min(i + batchSize, onesPositions.size());
                    List<int[]> batch = onesPositions.subList(i, end);
                    futures.add(executor.submit(new DiagonalTask(batch)));
                }

                int maxResult = 0;
                for (Future<Integer> future : futures) {
                    try {
                        maxResult = Math.max(maxResult, future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        Thread.currentThread().interrupt();
                        return maxResult;
                    }
                }

                return maxResult;
            } finally {
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    /*
     * class Solution {
     * private static final Map<String, int[]> map = new HashMap<>();
     * static {
     * init();
     * }
     * private int[][] grid;
     * 
     * private static void init() {
     * map.put("TL", new int[] { -1, -1 });
     * map.put("TR", new int[] { -1, 1 });
     * map.put("BL", new int[] { 1, -1 });
     * map.put("BR", new int[] { 1, 1 });
     * }
     * 
     * private int nextNum(int num) {
     * return num == 2 ? 0 : 2;
     * }
     * 
     * private int solve(Pair curr, String dir, boolean turned, int num) {
     * int distance = 0, currnum = grid[curr.row][curr.col];
     * int r = curr.row, c = curr.col;
     * if (turned) {
     * int[] direction = map.get(dir);
     * distance++;
     * r += direction[0];
     * c += direction[1];
     * while (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length &&
     * nextNum(currnum) == grid[r][c]) {
     * distance++;
     * currnum = grid[r][c];
     * r += direction[0];
     * c += direction[1];
     * }
     * return distance;
     * }
     * int d1 = 0, d2 = 0;
     * if (dir.equals("TR")) {
     * if (r - 1 >= 0 && c + 1 < grid[0].length && grid[r - 1][c + 1] ==
     * nextNum(num)) {
     * d1 = solve(new Pair(r - 1, c + 1), "TR", false, grid[r - 1][c + 1]);
     * }
     * if (r + 1 < grid.length && c + 1 < grid[0].length && grid[r + 1][c + 1] ==
     * nextNum(num)) {
     * d2 = solve(new Pair(r + 1, c + 1), "BR", true, grid[r + 1][c + 1]);
     * }
     * } else if (dir.equals("TL")) {
     * if (r - 1 >= 0 && c + 1 < grid[0].length && grid[r - 1][c + 1] ==
     * nextNum(num)) {
     * d1 = solve(new Pair(r - 1, c + 1), "TR", true, grid[r - 1][c + 1]);
     * }
     * if (r - 1 >= 0 && c - 1 >= 0 && grid[r - 1][c - 1] == nextNum(num)) {
     * d2 = solve(new Pair(r - 1, c - 1), "TL", false, grid[r - 1][c - 1]);
     * }
     * } else if (dir.equals("BR")) {
     * if (r + 1 < grid.length && c + 1 < grid[0].length && grid[r + 1][c + 1] ==
     * nextNum(num)) {
     * d1 = solve(new Pair(r + 1, c + 1), "BR", false, grid[r + 1][c + 1]);
     * }
     * if (r + 1 < grid.length && c - 1 >= 0 && grid[r + 1][c - 1] == nextNum(num))
     * {
     * d2 = solve(new Pair(r + 1, c - 1), "BL", true, grid[r + 1][c - 1]);
     * }
     * } else {
     * if (r - 1 >= 0 && c - 1 >= 0 && grid[r - 1][c - 1] == nextNum(num)) {
     * d1 = solve(new Pair(r - 1, c - 1), "TL", true, grid[r - 1][c - 1]);
     * }
     * if (r + 1 < grid.length && c - 1 >= 0 && grid[r + 1][c - 1] == nextNum(num))
     * {
     * d2 = solve(new Pair(r + 1, c - 1), "BL", false, grid[r + 1][c - 1]);
     * }
     * }
     * return 1 + Math.max(d1, d2);
     * }
     * 
     * public int lenOfVDiagonal(int[][] grid) {
     * this.grid = grid;
     * int m = grid.length, n = grid[0].length;
     * int ans = 0;
     * for (int i = 0; i < m; i++) {
     * for (int j = 0; j < n; j++) {
     * if (grid[i][j] == 1) {
     * ans = Math.max(ans, 1);
     * if (i + 1 < m && j + 1 < n && grid[i + 1][j + 1] == 2) {
     * Pair temp = new Pair(i + 1, j + 1);
     * int d = solve(temp, "BR", false, 2) + 1;
     * if (d > ans)
     * ans = d;
     * }
     * if (i + 1 < m && j - 1 >= 0 && grid[i + 1][j - 1] == 2) {
     * Pair temp = new Pair(i + 1, j - 1);
     * int d = solve(temp, "BL", false, 2) + 1;
     * if (d > ans)
     * ans = d;
     * }
     * if (i - 1 >= 0 && j + 1 < n && grid[i - 1][j + 1] == 2) {
     * Pair temp = new Pair(i - 1, j + 1);
     * int d = solve(temp, "TR", false, 2) + 1;
     * if (d > ans)
     * ans = d;
     * }
     * if (i - 1 >= 0 && j - 1 >= 0 && grid[i - 1][j - 1] == 2) {
     * Pair temp = new Pair(i - 1, j - 1);
     * int d = solve(temp, "TL", false, 2) + 1;
     * if (d > ans)
     * ans = d;
     * }
     * }
     * }
     * }
     * return ans;
     * }
     * 
     * private class Pair {
     * int row;
     * int col;
     * 
     * Pair(int row, int col) {
     * this.row = row;
     * this.col = col;
     * }
     * }
     * }
     */
}
