## Optimized

```
func putMarbles(weights []int, k int) int64 {
    n := len(weights)
    if n == k {
        return 0
    }
    pair := make([]int , n - 1)
    for i := 0 ; i < n - 1 ; i++ {
        pair[i] = weights[i] + weights[i + 1]
    }
    sort.Ints(pair)
    var ans int64
    for i := 0 ; i < k - 1 ; i++ {
        ans += int64(pair[n - 2 - i] - pair[i])
    }
    return ans
}

```