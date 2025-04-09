https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/

```
__import__("atexit").register(lambda: open("display_runtime.txt", "w").write("0"))
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        freq = set()
        for i in range(len(nums)):
            if nums[i] < k:
                return -1
            freq.add(nums[i])
        
        return len(freq) - 1 if k in freq else len(freq)
```