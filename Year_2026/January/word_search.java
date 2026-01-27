package January;

public class word_search {
    static void main() {

    }

    static class Solution {
        private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public boolean isWordExist(char[][] mat, String word) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[i].length; j++) {
                    if (mat[i][j] == arr[0]) {
                        mat[i][j] = '.';
                        if (exist(i, j, mat, arr, 1)) return true;
                        mat[i][j] = arr[0];
                    }
                }
            }
            return false;
        }

        private boolean exist(int row, int col, char[][] mat, char[] arr, int index) {
            if (index >= arr.length) return true;
            for (int[] dir : directions) {
                int newX = dir[0] + row, newY = dir[1] + col;
                if (newX >= 0 && newY >= 0 && newX < mat.length && newY < mat[0].length) {
                    if (mat[newX][newY] == arr[index]) {
                        mat[newX][newY] = '.';
                        if (exist(newX, newY, mat, arr, index + 1)) return true;
                        mat[newX][newY] = arr[index];
                    }
                }
            }
            return false;
        }
    }
}
