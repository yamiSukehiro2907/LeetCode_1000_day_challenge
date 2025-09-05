public class L_5 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.makeTheIntegerZero(5, 7));
    }

    static class Solution {
        public int makeTheIntegerZero(int num1, int num2) {
            for (int operation = 1; operation < 60; operation++) {
                long num = 1L * num1 - 1L * num2 * operation;
                if (num <= operation) {
                    return -1;
                }
                if (Long.bitCount(num) <= operation) {
                    return operation;
                }
            }
            return -1;
        }
    }
}
