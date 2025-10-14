import java.util.List;

public class L_14 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
            int n = nums.size();
            for (int i = 0; i + 2 * k <= n; i++) {
                if (isIncreasing(i, i + k - 1, nums, k)) {
                    if (isIncreasing(i + k, i + 2 * k - 1, nums, k)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isIncreasing(int startIndex, int endIndex, List<Integer> nums, int k) {
            for (int i = startIndex; i < endIndex; i++) {
                if (nums.get(i) >= nums.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }
    }
}
