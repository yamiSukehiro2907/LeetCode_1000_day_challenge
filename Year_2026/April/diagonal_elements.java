package April;

import java.util.ArrayList;

public class diagonal_elements {
    static void main() {

    }

    static class Solution {
        static ArrayList<Integer> diagView(int[][] mat) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int col = 0; col < mat[0].length; col++) {
                int i = 0, j = col;
                while (i < mat.length && j >= 0) list.add(mat[i++][j--]);
            }
            for (int row = 1; row < mat.length; row++) {
                int i = row, j = mat[0].length - 1;
                while (i < mat.length && j >= 0) list.add(mat[i++][j--]);
            }
            return list;
        }
    }

}
