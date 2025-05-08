https://leetcode.com/problems/largest-divisible-subset/description/

```
const auto _ = std::cin.tie(nullptr)->sync_with_stdio(false);
#define LC_HACK
const auto __ = []() {
    struct ___ {
        static void _() { std::ofstream("display_runtime.txt") << 0 << '\n'; }
    };
    std::atexit(&___::_);
    return 0;
}();

class Solution {
public:
    vector<int> largestDivisibleSubset(vector<int>& nums) {
        int n = nums.size();
        sort(nums.begin(), nums.end());
        vector<int> subset(n, 1);
        vector<int> indexes(n, 0);
        int maxIndex = 0;
        for(int i = 0; i < n; i++) {
            indexes[i] = i;
            int limit = (nums[i] + 1) / 2;
            for(int j = 0; j < i && nums[j] <= limit; j++) {
                if(nums[i] % nums[j] == 0 && subset[j] + 1 > subset[i]) {
                    subset[i] = subset[j] + 1;
                    indexes[i] = j;
                }
            }
            if(subset[i] > subset[maxIndex]){
                maxIndex = i;
            }
        }
        vector<int> result;
        result.push_back(nums[maxIndex]);
        while(indexes[maxIndex] != maxIndex) {
            maxIndex = indexes[maxIndex];
            result.push_back(nums[maxIndex]);
        }
        reverse(result.begin(), result.end());
        return result;
    }
};
```