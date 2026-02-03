package February;

public class trionic_array_one {
    static void main() {

    }

    static class Solution {
        public boolean isTrionic(int[] nums) {
            int n = nums.length;
            int p = 0, q = 0;
            while (p < nums.length - 1 && nums[p] < nums[p + 1]) p++;
            if (p == 0) return false;
            q = p;
            while (q < nums.length - 1 && nums[q] > nums[q + 1]) q++;
            if (q == p || q == nums.length - 1) return false;
            p = q;
            while (p < nums.length - 1 && nums[p] < nums[p + 1]) p++;
            return p == nums.length - 1;
        }
    }
}
