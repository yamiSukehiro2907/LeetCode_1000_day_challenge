package February;

import java.util.Arrays;

public class minimum_removal_to_balance_array {
    static void main() {

    }

    static class Solution {
        public int minRemoval(int[] nums, int k) {
            int n = nums.length;
            if (n == 1) return 0;
            int minRemoval = n;
            int left = 0;
            Arrays.sort(nums);
            for (int right = 1; right < n; right++) {
                while (left <= right && ((long) nums[left] * k) < nums[right]) left++;
                int elementsRemoved = n - right + left - 1;
                if (minRemoval > elementsRemoved) minRemoval = elementsRemoved;
            }
            return minRemoval;
        }
    }
}
