https://leetcode.com/problems/partition-equal-subset-sum/description/

```
import java.math.BigInteger;
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        BigInteger bit = BigInteger.ONE;
        for (int num : nums) {
            bit = bit.or(bit.shiftLeft(num));
            if (bit.testBit(target)) return true;
        }
        return false;
    }
}

/*
class Solution {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int num : nums) total += num;
        if(total % 2 != 0) return false;
        total /= 2;
        return canGet(nums , total);
    }
    private boolean canGet(int[] nums , int target){
        Boolean[] memo = new Boolean[target + 1];
        return solve(nums, target , nums.length - 1 , memo);
    }
    private boolean solve(int[] nums , int target , int index , Boolean[] memo){
        if(target == 0) return true;
        if(target < 0) return false;
        if(index == 0) return target == nums[0];
        if(memo[target] != null) return memo[target];
        return memo[target] = (solve(nums , target - nums[index] , index - 1 , memo) || solve(nums , target , index - 1 , memo));
    }
}
 */
```
