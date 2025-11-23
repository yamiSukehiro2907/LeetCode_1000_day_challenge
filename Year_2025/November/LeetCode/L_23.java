public class L_23 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 4, 4};
        Solution solution = new Solution();
        System.out.println(solution.maxSumDivThree(arr));
    }

    private static class Solution {
        static {
            Solution solution = new Solution();
            int[] arr = {3};
            for (int i = 0; i < 100; i++) solution.maxSumDivThree(arr);
        }

        public int maxSumDivThree(int[] nums) {
            if (nums.length == 1) return nums[0] % 3 == 0 ? nums[0] : 0;
            int[] ones = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            int[] twos = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            int minOneRem = Integer.MAX_VALUE, minTwoRem = Integer.MAX_VALUE;
            /// store the numbers which have to be removed when totalSum is not divisible by 3
            /// Case-1: If we got 1 remainder then either remove minimum number with remainder 1 or sum of two numbers with whose remainder is 2 (by combining they create 1)
            /// Case-2: If we got 2 remainder then either remove minimum number with remainder 2 or sum of two numbers with whose remainder is 1 (by combining they create 2)
            int sum = 0;
            for (int num : nums) {
                sum += num;
                if (num % 3 == 1) {
                    if (minOneRem > num) minOneRem = num;
                    fix(ones, num);
                } else if (num % 3 == 2) {
                    if (minTwoRem > num) minTwoRem = num;
                    fix(twos, num);
                }
            }
            if (sum % 3 == 0) return sum;
            if (sum % 3 == 1)
                return Math.max(0, Math.max(sum - ((twos[1] == Integer.MAX_VALUE || twos[0] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (twos[0] + twos[1])), sum - minOneRem));
            if (sum % 3 == 2)
                return Math.max(0, Math.max(sum - ((ones[1] == Integer.MAX_VALUE || ones[0] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (ones[0] + ones[1])), sum - minTwoRem));
            return 0;
        }

        private void fix(int[] temp, int num) {
            /// keep one storing the minimum unique numbers
            if (num < temp[0]) {
                temp[1] = temp[0];
                temp[0] = num;
            } else if (num < temp[1]) temp[1] = num;
        }
    }
}
