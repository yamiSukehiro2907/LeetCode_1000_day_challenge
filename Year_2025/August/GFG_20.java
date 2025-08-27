public class GFG_20 {
    public static void main(String[] args) {
        int[][] mat = { { 7, 8, 9, 10 },
                { 11, 12, 13, 1 },
                { 2, 3, 4, 5 } };
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(mat, 3));
    }

    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int rows = matrix.length, cols = matrix[0].length;
            int length = rows * cols;
            int start = 0, end = length - 1;
            while (start <= end) {
                int mid = start + ((end - start) / 2);
                if (matrix[mid / cols][mid % cols] == target || matrix[start / cols][start % cols] == target
                        || matrix[end / cols][end % cols] == target) {
                    return true;
                } else {
                    
                }
            }
            return false;
        }
    }
}
