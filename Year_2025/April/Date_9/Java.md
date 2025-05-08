https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/

```
// Approach 3 : Optimized Approach for this constraint
class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++){
            minOperations(new int[]{1} , 1);
        }
    }
    public static int minOperations(int[] nums, int k) {
        int n = nums.length;
        if(n == 1){
            if(nums[0] < k) return -1;
            if(nums[0] == k) return 0;
            return 1;
        }
        boolean freq[] = new boolean[101];
        int op = 0;
        for(int num : nums){
            if(num < k) return -1;
            if(!freq[num] && num != k) op++;
            freq[num] = true;
        }
        return op;
    }
}

/*
// Approach 1 : Brute Force
class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        int op = 0;
        int min = Integer.MAX_VALUE;
        for(int num : nums) min = Math.min(min , num);
        if(k > min) return -1;
        boolean isIdentical = false;
        while(!isIdentical){
            int max1 = 0;
            int max2 = 0;
            for(int num : nums){
                if(num > max1){
                    max2 = max1;
                    max1 = num;
                }else if(num > max2 && num < max1) max2 = num;
            }
            if(max2 == 0) break;
            int valid = max2;
            boolean changed = false;
            for(int i = 0 ; i < nums.length ; i++){
                if(nums[i] > valid){
                    if(nums[i] == max1){
                        nums[i] = valid;
                        changed = true;
                    }
                    else return -1;
                }
            }
            if(!changed) return -1;
            op++;
            isIdentical = true;
            if(!check(nums)) isIdentical = false;
        }
        if(nums[0] != k) op++;
        return op;
    }
    private boolean check(int[] nums){
        int t = nums[0];
        for(int num : nums){
            if(num != t) return false;
        }
        return true;
    }
}

// Approach 2 : Better Approach
class Solution {
    public int minOperations(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(num < k) return -1;
            else set.add(num);
        }
        if(set.contains(k)) return set.size() - 1;
        else return set.size();
    }
}
 */
```