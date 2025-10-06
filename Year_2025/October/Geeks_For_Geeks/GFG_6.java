import java.util.*;

public class GFG_6 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        ArrayList<ArrayList<Integer>> ans = solution.knightTour(n);
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static class Solution {
        private int n;
        private int[][] matrix;

        public ArrayList<ArrayList<Integer>> knightTour(int n1) {
            this.n = n1;
            matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(matrix[i], -1);
            }
            matrix[0][0] = 0;
            int visited = 0;
            if (!possible(0, 0, visited)) {
                return new ArrayList<>();
            }
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                ans.add(new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans.get(i).add(matrix[i][j]);
                }
            }
            return ans;
        }

        private static final int[][] directions = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 },
                { 2, -1 }, { 2, 1 } };

        private boolean possible(int i, int j, int visited) {
            if (visited == (n * n) - 1)
                return true;
            List<int[]> nextMoves = new ArrayList<>();
            for (int[] dir : directions) {
                int newX = dir[0] + i;
                int newY = dir[1] + j;
                if (newX < n && newX >= 0 && newY >= 0 && newY < n) {
                    if (matrix[newX][newY] == -1) {
                        int maxPossibleNeighbour = find(newX, newY);
                        nextMoves.add(new int[] { newX, newY, maxPossibleNeighbour });
                    }
                }
            }
            Collections.sort(nextMoves, (a, b) -> a[2] - b[2]);
            for (int[] move : nextMoves) {
                matrix[move[0]][move[1]] = visited + 1;
                if (possible(move[0], move[1], visited + 1)) {
                    return true;
                }
                matrix[move[0]][move[1]] = -1;
            }
            return false;
        }

        private int find(int x, int y) {
            int count = 0;
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX < n && newX >= 0 && newY < n && newY >= 0 && matrix[newX][newY] != -1) {
                    count++;
                }
            }
            return count;
        }
    }

}
