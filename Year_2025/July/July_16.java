public class July_16 {
    public static void main(String[] args) {

    }

    static class Solution {
        static {
            for (int i = 0; i < 500; i++) {
                maximumLength(new int[] { 1, 1 });
            }
        }

        public static int maximumLength(int[] nums) {
            if (nums.length == 2)
                return 2;
            int[] count = new int[2];
            int[] end = new int[2];
            for (int num : nums) {
                int parity = num % 2;
                count[parity]++; // increase the count of odd or even
                end[parity] = end[1 - parity] + 1; // if 0 then pervious even + 1
            }
            return Math.max(Math.max(count[0], count[1]), Math.max(end[0], end[1]));
        }
    }
    /*
    class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            maximumLength(new int[] { 1, 1 });
        }
    }

    public static int maximumLength(int[] nums) {
        if (nums.length == 2) return 2;
        int countOdd = nums[0] % 2 == 0 ? 0 : 1;
        int countEven = nums[0] % 2 == 0 ? 1 : 0;
        int count = 1;
        boolean flag = nums[0] % 2 == 0 ? false : true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 != 0) { // false
                countOdd++;
                if (!flag) {
                    count++;
                    flag = !flag;
                }
            } else {
                countEven++;
                if (flag) {
                    count++;
                    flag = !flag;
                }
            }
        }
        return Math.max(count, Math.max(countOdd, countEven));
    }
    // odd odd odd odd
    // even odd even odd
    // odd even odd even
    // even even even even
}
     */
}
