package March;

public class special_positions_in_binary_matrix {

    static void main() {

    }

    static class Solution {
        public int numSpecial(int[][] mat) {
            int count = 0;
            for (int i = 0; i < mat.length; i++) {
                int index = findColIndex(mat, i);
                if (index >= 0 && onlyOneInCol(mat, index, i)) count++;
            }
            return count;
        }

        private int findColIndex(int[][] mat, int row) {
            int index = -1;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[row][j] == 1) {
                    if (index == -1) index = j;
                    else return -1;
                }
            }
            return index;
        }

        private boolean onlyOneInCol(int[][] mat, int col, int row) {
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][col] == 1 && i != row) return false;
            }
            return true;
        }
    }
}
