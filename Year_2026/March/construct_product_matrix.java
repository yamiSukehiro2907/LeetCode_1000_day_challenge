package March;

public class construct_product_matrix {
    static void main() {

    }

    static class Solution {
        private final static int MOD = 12345;
        public int[][] constructProductMatrix(int[][] grid) {
            int m = grid.length , n = grid[0].length;
            int length = m * n;
            int[] prefix = new int[length];
            prefix[0] = grid[0][0] % MOD;
            for(int i = 1 ; i < length ; i++){
                int row = i / n , col = i % n;
                prefix[i] = ((prefix[i - 1] % MOD) * (grid[row][col] % MOD)) % MOD;
            }
            int suffix = 1;
            for(int i = length - 1 ; i > 0 ; i--){
                int row = i / n , col = i % n;
                int num = grid[row][col];
                grid[row][col] = ((suffix % MOD) * (prefix[i - 1] % MOD)) % MOD;
                suffix = ((suffix % MOD) * (num % MOD)) % MOD;
            }
            grid[0][0] = suffix;
            return grid;
        }
    }
}
