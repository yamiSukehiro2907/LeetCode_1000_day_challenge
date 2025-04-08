https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/

```
class Solution {
public:
    int minimumOperations(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<bool> freq(101, false);
        for (int i = n - 1; i >= 0; i--) {
            if (freq[nums[i]]) {
                return i / 3 + 1;
            } else {
                freq[nums[i]] = true;
            }
        }
        return 0;
    }
};

```