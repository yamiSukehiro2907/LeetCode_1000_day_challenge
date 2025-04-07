https://leetcode.com/problems/partition-equal-subset-sum/description/

```
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        total = sum(nums)
        if total % 2 != 0:
            return False
        target = total // 2

        bit = 1
        for num in nums:
            bit |= bit << num
            if (bit >> target) & 1:
                return True

        return False

```