https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/

```
class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        vector<bool> freq(101 , false);
        int op = 0;
        for(auto num : nums){
            if(num < k) return -1;
            if(!freq[num] && num != k) op++;
            freq[num] = true;
        }
        return op;
    }
};
```