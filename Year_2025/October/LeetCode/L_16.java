public class L_16 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[] { 3, 2, 1, 0, 0, 1, 2, 3, 4, 4 };
        System.out.println(solution.findSmallestInteger(arr, 5));
    }

    private static class Solution {
        public int findSmallestInteger(int[] nums, int value) {
            int[] rem = new int[value];
            for (int num : nums) {
                int r = num % value;
                if (r < 0) r += value;
                rem[r]++;
            }
            int k = rem[0];
            int pos = 0;
            for (int i = 0; i < value; i++) {
                if (rem[i] < k) {
                    pos = i;
                    k = rem[i];
                }
            }
            return value * k + pos;
            // 3 , 0 , 3 , 2 , 4 , 2, 1 , 1, 0, 4
        }
    }
}