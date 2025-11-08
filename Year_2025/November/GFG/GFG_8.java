
import java.util.HashMap;

public class GFG_8 {

    public static void main(String[] args) {
        int[][] coins = {{1, 2, 3}, {4, 6, 5}, {3, 2, 1}};
        Solution solution = new Solution();
        System.out.println(solution.numberOfPath(coins, 12));
    }

    private static class Solution {

        private int[][] matrix;
        private int maxCoin;
        private HashMap<String, Integer> map;

        public int numberOfPath(int[][] mat, int k) {
            this.maxCoin = k;
            this.matrix = mat;
            this.map = new HashMap<>();
            return findPaths(0, 0, 0);
        }

        private int findPaths(int i, int j, int sum) {
            if (i >= matrix.length || j >= matrix[0].length) {
                return 0;
            }
            sum += matrix[i][j];
            if ((i == matrix.length - 1) && (j == matrix[0].length - 1)) {
                return sum == maxCoin ? 1 : 0;
            }
            String key = i + "," + j + "," + sum;
            if (map.containsKey(key)) {
                return map.get(key);
            }
            int right = findPaths(i, j + 1, sum);
            int left = findPaths(i + 1, j, sum);
            return left + right;
        }
    }
}
