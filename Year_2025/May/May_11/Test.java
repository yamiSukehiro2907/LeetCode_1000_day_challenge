package Year_2025.May.May_11;

public class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.threeConsecutiveOdds(new int[] { 1, 2 }));
    }
}

class Solution {
    public boolean threeConsecutiveOdds(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return false;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] % 2 == 1 && nums[i + 1] % 2 == 1 && nums[i + 2] % 2 == 1)
                return true;
        }
        return false;
    }
}