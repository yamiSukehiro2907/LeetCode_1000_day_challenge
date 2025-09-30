
public class L_30 {
    public static void main(String[] args) {

    }

    private static class Solution {
        private static final int[][] PASCAL_MOD_5 = buildPascalMod5();

        public int triangularSum(int[] nums) {
            int n = nums.length;
            int rowIndex = n - 1;
            int result = 0;

            for (int col = 0; col < n; col++) {
                int coefficientMod2 = binomialMod2(rowIndex, col);
                int coefficientMod5 = binomialMod5(rowIndex, col);
                int coefficientMod10 = chineseRemainderMod10(coefficientMod2, coefficientMod5);
                result = (result + coefficientMod10 * nums[col]) % 10;
            }

            return result;
        }

        private int binomialMod2(int n, int k) {
            return ((k & (n - k)) == 0) ? 1 : 0;
        }

        private int binomialMod5(int n, int k) {
            int product = 1;
            while (n > 0 || k > 0) {
                int nDigit = n % 5;
                int kDigit = k % 5;
                if (kDigit > nDigit)
                    return 0;
                product = (product * PASCAL_MOD_5[nDigit][kDigit]) % 5;
                n /= 5;
                k /= 5;
            }
            return product;
        }

        private int chineseRemainderMod10(int residueMod2, int residueMod5) {
            int result = residueMod5;
            if ((result & 1) != residueMod2) {
                result += 5;
            }
            return result;
        }

        private static int[][] buildPascalMod5() {
            int[][] pascal = new int[5][5];
            for (int row = 0; row < 5; row++) {
                pascal[row][0] = pascal[row][row] = 1;
                for (int col = 1; col < row; col++) {
                    pascal[row][col] = (pascal[row - 1][col - 1] + pascal[row - 1][col]) % 5;
                }
            }
            return pascal;
        }
    }
}
