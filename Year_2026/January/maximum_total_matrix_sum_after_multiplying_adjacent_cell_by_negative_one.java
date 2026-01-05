package January;

public class maximum_total_matrix_sum_after_multiplying_adjacent_cell_by_negative_one {
    static void main() {

    }

    static class Solution {
        public long maxMatrixSum(int[][] matrix) {
            long totalSum = 0;
            int totalNegatives = 0 , minNum = 100001;
            for(int[] row : matrix){
                for(int num : row){
                    if(num < 0){
                        totalNegatives++;
                        num *= -1;
                    }
                    totalSum += num;
                    if(minNum > num) minNum = num;
                }
            }
            return (totalNegatives % 2 == 0) ? totalSum : (totalSum - 2L * minNum);
        }
    }
}
