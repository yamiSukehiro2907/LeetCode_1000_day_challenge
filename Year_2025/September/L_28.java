public class L_28 {

    public static void main(String[] args) {

    }

    private static class Solution {
        public int largestPerimeter(int[] nums) {
            int n = nums.length;
            findLargestAndSwap(nums, n - 1); // finding the first max
            findLargestAndSwap(nums, n - 2); // finding the second max
            for (int i = n - 1; i > 1; i--) {
                findLargestAndSwap(nums, i - 2); // finding the third max
                if (nums[i] < nums[i - 1] + nums[i - 2]) return nums[i] + nums[i - 1] + nums[i - 2];
            }
            return 0;
        }

        private void findLargestAndSwap(int[] nums, int index) {
            int maxIndex = 0;
            for (int i = 0; i <= index; i++) {
                if (nums[maxIndex] < nums[i]) maxIndex = i;
            }
            int temp = nums[index];
            nums[index] = nums[maxIndex];
            nums[maxIndex] = temp;
        }
    }
    /*
    class Solution {
    static {
        System.gc();
        Solution solution = new Solution();
        for (int i = 0; i < 500; i++) {
            solution.largestPerimeter(new int[1]);
        }
    }

    public int largestPerimeter(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;
        Arrays.sort(nums);
        for (int i = n - 1; i > 1; i--) {
            if (nums[i] < nums[i - 1] + nums[i - 2]) {
                return nums[i - 1] + nums[i - 2] + nums[i];
            }
        }
        return 0;
    }
}
     */
}
