https://leetcode.com/problems/largest-divisible-subset/description/

```
func largestDivisibleSubset(nums []int) []int {
    n := len(nums)
    if n == 0 {
		return []int{}
	}
    sort.Ints(nums)
    maxIndex := 0
    subset := make([]int , n)
    indexes := make([]int , n)
    for i := 0 ; i < n ; i++ {
        subset[i] = 1
        limit := (nums[i] + 1)/2
        indexes[i] = i
        for j := 0 ; j < i && nums[j] <= limit ; j++ {
            if nums[i] % nums[j] == 0 && subset[j] + 1 > subset[i] {
                subset[i] = subset[j] + 1
                indexes[i] = j
            }
        }
        if subset[i] > subset[maxIndex] {
            maxIndex = i
        }
    }
    var ans []int
    ans = append(ans , nums[maxIndex])
    for maxIndex != indexes[maxIndex] {
        maxIndex = indexes[maxIndex]
        ans = append(ans , nums[maxIndex])
    }
    return ans
}

```
