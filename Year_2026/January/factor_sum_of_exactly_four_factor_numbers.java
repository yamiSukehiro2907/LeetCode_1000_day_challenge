package January;

import java.io.FileWriter;

public class factor_sum_of_exactly_four_factor_numbers {
    static void main() {
        Solution sol = new Solution();
        int[] arr = {1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10};
        System.out.println(sol.sumFourDivisors(arr));
    }

    static class Solution {
        static{
            Solution solution = new Solution();
            int[] arr = {1};
            for(int i = 0 ; i < 100 ; i++) solution.sumFourDivisors(arr);
        }
        public int sumFourDivisors(int[] nums) {
            int totalSum = 0, lastDivisor = 0, sqrtNum = 0;
            for (int num : nums) {
                sqrtNum = (int) (Math.sqrt(num));
                if (sqrtNum * sqrtNum == num) continue;
                for (int div = 2; div <= sqrtNum; div++) {
                    if (num % div == 0) {
                        if (lastDivisor == 0) lastDivisor = div;
                        else {
                            lastDivisor = 0;
                            break;
                        }
                    }
                }
                if (lastDivisor > 0 && lastDivisor * lastDivisor < num) totalSum += (1 + num + lastDivisor + num / lastDivisor);
                lastDivisor = 0;
            }
            return totalSum;
        }
    }
}
