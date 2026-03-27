package March;

import java.util.HashMap;
import java.util.Map;

public class chocolates_pickup {
    static void main() {
        int[][] grid = {
                {4, 1, 2},
                {3, 6, 1},
                {1, 6, 6},
                {3, 1, 2}
        };
        Solution sol = new Solution();
        System.out.println(sol.maxChocolate(grid));
    }

    static class Solution {
        private Map<String, Integer> dp;
        private int[][] grid;

        public int maxChocolate(int grid[][]) {
            this.dp = new HashMap<>();
            this.grid = grid;
            return find(0, 0, grid[0].length - 1);
        }

        private int find(int row, int col1, int col2) {
            if (row >= grid.length || col1 < 0 || col2 < 0 || col1 >= grid[0].length || col2 >= grid[0].length)
                return 0;
            String key = row + "," + col1 + "," + col2;
            if (dp.containsKey(key)) return dp.get(key);
            int cellValue = (col1 == col2) ? grid[row][col1] : (grid[row][col1] + grid[row][col2]);
            int maxValue = Integer.MIN_VALUE;
            for (int dir1 : directions) {
                int c1 = dir1 + col1;
                if (c1 < 0 || c1 > grid[0].length) continue;
                for (int dir2 : directions) {
                    int c2 = dir2 + col2;
                    if (c2 < 0 || c2 > grid[0].length) continue;
                    int value = find(row + 1, c1, c2) + cellValue;
                    if (value > maxValue) maxValue = value;
                }
            }
            dp.put(key, (maxValue == Integer.MIN_VALUE) ? cellValue : maxValue);
            return (maxValue == Integer.MIN_VALUE) ? cellValue : maxValue;
        }

        private static final int[] directions = {-1, 0, 1};
    }
}

