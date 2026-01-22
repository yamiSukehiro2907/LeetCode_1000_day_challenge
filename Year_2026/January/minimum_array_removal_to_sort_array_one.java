package January;

public class minimum_array_removal_to_sort_array_one {
    static void main() {

    }

    static class Solution {
        public int minimumPairRemoval(int[] nums) {
            if (nums.length == 1) return 0;
            int totalElement = nums.length;
            boolean isSorted;
            while (true) {
                isSorted = true;
                int minIndex = 1;
                for (int i = 1; i < totalElement; i++) {
                    if (nums[i] + nums[i - 1] < nums[minIndex] + nums[minIndex - 1]) minIndex = i;
                    if (nums[i - 1] > nums[i]) isSorted = false;
                }
                if (isSorted) break;
                int sum = nums[minIndex] + nums[minIndex - 1];
                nums[minIndex - 1] = sum;
                totalElement--;
                for (int i = minIndex; i < totalElement; i++) nums[i] = nums[i + 1];
            }
            return nums.length - totalElement;
        }
    }
}
