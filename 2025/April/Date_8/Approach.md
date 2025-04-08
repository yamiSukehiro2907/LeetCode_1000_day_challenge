https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/

## Approach : Brute Force

- We can create a set 
- Start checking from  i = 0 
- In case if we get any duplicates 
   -  We clear the set and move to starting pointer by plus 3 and increase the no of operation by 1 
   -  else return no of operation


```
class Solution {
    public int minimumOperations(int[] nums) {
       int ans = 0;
       Set<Integer> set = new HashSet<>();
       int n = nums.length;
       boolean can = true;
       int i = 0;
       while(can){
        can = false;
        for(int index = i ; index < n ; index++){
            if(set.contains(nums[index])){
                i += 3;
                ans++;
                set.clear();
                can = true;
                break;
            }
            set.add(nums[index]);
        }
       }
       return ans;
    }
}
```

## Optimized Approach

- We create either a boolean of max constraint + 1 OR a set (Here as we have 100 as max value of nums[i] so I suggest creating a boolean instead of set)
- We check from the last
- If we get any repeating number then it means that we have to remove elements upto that number 
- As the indexing is based on 0 therefore number of operations will be index / 3 + 1