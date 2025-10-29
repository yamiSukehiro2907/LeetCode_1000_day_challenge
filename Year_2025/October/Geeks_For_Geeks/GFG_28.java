
import java.util.*;

public class GFG_28 {

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1},
            {1, 0}
        };
                Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> ans = solution.nearest(grid);
        for (ArrayList<Integer> temp : ans) {
            System.out.println(temp);
        }

    }

    private static class Solution {
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        public ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
            int rows = grid.length, cols = grid[0].length;
            Queue<Pair> queue = new LinkedList<>();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        queue.add(new Pair(i, j));
                    }
                }
            }
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        temp.add(0);
                    } else {
                        temp.add(Integer.MAX_VALUE);
                    }
                }
                ans.add(temp);
            }
            int distance = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Pair temp = queue.poll();
                    for (int[] dir : directions) {
                        int newX = dir[0] + temp.xCord;
                        int newY = dir[1] + temp.yCord;
                        if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 0) {
                            int dist = ans.get(newX).get(newY);
                            if (dist > distance) {
                                System.out.println("Entered");
                                ans.get(newX).set(newY, distance);
                                queue.add(new Pair(newX, newY));
                            }
                        }
                    }
                }
                distance++;
            }
            return ans;
        }

        private class Pair {

            int xCord;
            int yCord;

            public Pair(int xCord, int yCord) {
                this.xCord = xCord;
                this.yCord = yCord;
            }
        }
    }
}
