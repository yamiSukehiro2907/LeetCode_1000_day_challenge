package December.LeetCode;

public class k_smallest_in_sorted_matrix {
    static void main() {

    }

    static class Solution {
        public int kthSmallest(int[][] mat, int k) {
            if (k == 1) return mat[0][0];
            if (k == mat.length * mat.length) return mat[mat.length - 1][mat.length - 1];
            int start = mat[0][0], end = mat[mat.length - 1][mat.length - 1];
            int ans = 0;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);
                if (count(mid, mat) < k) start = mid + 1;
                else {
                    ans = mid;
                    end = mid - 1;
                }
            }
            return ans;
        }

        private int count(int target, int[][] mat) {
            int row = 0, col = mat.length - 1;
            int totalCount = 0;
            while (row < mat.length && col >= 0) {
                int num = mat[row][col];
                if (num <= target) {
                    totalCount += (col + 1);
                    row++;
                } else col--;
            }
            return totalCount;
        }
    }


}
