https://leetcode.com/problems/largest-divisible-subset/description/

```
class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        subset = [1] * n
        indexes = list(range(n))
        maxIndex = 0
        for i in range(n):
            limit = (nums[i] + 1) // 2 
            for j in range(i):
                if nums[j] <= limit:
                    if nums[i] % nums[j] == 0 and subset[j] + 1 > subset[i]:
                        subset[i] = subset[j] + 1
                        indexes[i] = j
                else:
                    break  
            
            if subset[i] > subset[maxIndex]:
                maxIndex = i
        
        ans = []
        ans.append(nums[maxIndex])
        while indexes[maxIndex] != maxIndex:
            maxIndex = indexes[maxIndex]
            ans.append(nums[maxIndex])
        
        ans.reverse()
        return ans
```
