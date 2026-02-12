package February;

import java.util.Arrays;

public class transformed_array{
  public static void main(String[] args){
    Solution solution = new Solution();
    int[] arr = {-1 , 2 , -4 , 4};
    System.out.println(Arrays.toString(solution.constructTransformedArray(arr)));
  }

  static class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int[] ans = new int[nums.length];
        for(int i = 0 ; i < nums.length ; i++) ans[i] = nums[((i + nums[i]) % nums.length + nums.length) % nums.length];
        return ans;
    }
  }
}
