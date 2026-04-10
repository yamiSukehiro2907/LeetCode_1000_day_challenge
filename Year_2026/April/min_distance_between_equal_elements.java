package April;

public class min_distance_between_equal_elements {
    static void main() {

    }

    static class Solution {
        public int minimumDistance(int[] nums) {
            int n = nums.length;
            int minDis = n + 1;
            for (int i = 0; i + 2 < n; i++) {
                if (minDis == 2) break;
                for (int j = i + 1; j + 1 < n; j++) {
                    if (minDis == 2) break;
                    if (nums[i] != nums[j]) continue;
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] == nums[k]) {
                            if (minDis > k - i) minDis = k - i;
                            break;
                        }
                    }
                }
            }
            return minDis == n + 1 ? -1 : 2 * minDis;
        }
    }
}
