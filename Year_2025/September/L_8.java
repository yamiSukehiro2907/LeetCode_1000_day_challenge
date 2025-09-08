import java.util.Arrays;

public class L_8 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.getNoZeroIntegers(11)));
    }

    private static class Solution {
        public int[] getNoZeroIntegers(int n) {
            for (int num2 = n - 1; num2 >= 0; num2--) {
                if (valid(num2)) {
                    System.out.println("Valid num2: " + num2);
                    if (valid(n - num2))
                        return new int[] { num2, n - num2 };
                }
            }
            return new int[] {};
        }

        private boolean valid(int num2) {
            int temp = num2;
            while (temp > 0) {
                System.out.println("temp: " + temp);
                int rem = temp % 10;
                System.out.println("rem: " + rem);
                if (rem == 0)
                    return false;
                temp /= 10;
            }
            return true;
        }
    }
}