## Brute Force 

```
class Solution:
    def putMarbles(self, weights: List[int], k: int) -> int:
        n = len(weights)
        if n == k:
            return 0
        pair = []
        for i in range(n - 1):
            pair.append(weights[i] + weights[i + 1])
        pair.sort()
        ans = 0
        for i in range(k - 1):
            ans += pair[n - 2 - i] - pair[i]
        return ans
```


## Optimized

```
class Solution:
    def putMarbles(self, weights: List[int], k: int) -> int:
        if len(weights) == k or k == 1:
            return 0
        wt_sum = [weights[i]+weights[i+1] for i in range(len(weights)-1)]
        wt_sum.sort()
        return sum(wt_sum[-(k-1):]) - sum(wt_sum[:k-1])

```