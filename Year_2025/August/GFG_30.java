public class GFG_30 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = { { 1, 1 }, { 1, 1 } };
        System.out.println(solution.celebrity(arr));
    }

    static class Solution {
        public int celebrity(int mat[][]) {
            int n = mat.length;
            for (int i = 0; i < n; i++) {
                boolean found = true;
                for (int j = 0; j < n ; j++) {
                    if (i != j && mat[i][j] == 1) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    System.out.println("found: " + i);
                    boolean is = true;
                    for (int j = 0; j < n; j++) {
                        if (mat[j][i] == 0) {
                            is = false;
                            break;
                        }
                    }
                    if (is)
                        return i;
                }
            }
            return -1;
        }
    }
}
