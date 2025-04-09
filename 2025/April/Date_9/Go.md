https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/description/

```
import "sort"

func minOperations(nums []int, k int) int {
    sort.Ints(nums)
    if nums[0] < k{ return -1 }
    res := 0
    for i := len(nums) - 1; i >= 1; i-- {
        if nums[i] != nums[i-1] {
           res++
        }
    }
    if nums[0] > k {
        res++
    }
    return res
}
```