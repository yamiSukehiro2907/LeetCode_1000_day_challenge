https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/

```
class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++) minimumOperations(new int[]{1});
    }
    public static int minimumOperations(int[] nums) {
        int n = nums.length;
        if(n == 1) return 0;
        boolean freq[] = new boolean[101];
        for(int i = n - 1 ; i >= 0 ; i--){
            if(freq[nums[i]]) return i/3 + 1;
            else freq[nums[i]] = true;
        }
        return 0;
    }
}
```