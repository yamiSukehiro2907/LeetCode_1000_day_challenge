package March;

public class minimum_swaps_to_arrange_binary_grid {
    static void main() {

    }

    static class Solution {
        public int minSwaps(int[][] grid) {
            final int n = grid.length;
            int ans = 0;
            int[] suffixZeros = new int[n];
            for (int i = 0; i < grid.length; ++i) suffixZeros[i] = getSuffixZeroCount(grid[i]);
            for (int i = 0; i < n; ++i) {
                final int neededZeros = n - 1 - i;
                final int j = getFirstRowWithEnoughZeros(suffixZeros, i, neededZeros);
                if (j == -1) return -1;
                for (int k = j; k > i; --k) suffixZeros[k] = suffixZeros[k - 1];
                ans += j - i;
            }
            return ans;
        }

        private int getSuffixZeroCount(int[] row) {
            for (int i = row.length - 1; i >= 0; --i) if (row[i] == 1) return row.length - i - 1;
            return row.length;
        }

        private int getFirstRowWithEnoughZeros(int[] suffixZeros, int i, int neededZeros) {
            for (int j = i; j < suffixZeros.length; ++j) if (suffixZeros[j] >= neededZeros) return j;
            return -1;
        }
    }

}
