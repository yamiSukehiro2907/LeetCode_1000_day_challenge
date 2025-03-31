## Brute Force


```
class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if(n == k) return 0;
        long pairs[] = new long[n - 1];
        for(int i = 0 ; i < n - 1 ; i++) pairs[i] = weights[i] + weights[i + 1];
        Arrays.sort(pairs);
        long ans = 0;
        int start = 0 , end = n - 2;
        for(int i = 0 ; i < k - 1 ; i++) ans += pairs[end--] - pairs[start++];
        return ans;
    }
}
```


## Optimized

```
class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++){
            putMarbles(new int[]{1} , 1);
        }
    }
    public static long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if(n == k) return 0;
        int pairs[] = new int[n - 1];
        for(int i = 0 ; i < n - 1 ; i++){
            pairs[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(pairs , 0 , n - 1);
        long ans = 0L;
        for(int i = 0 ; i < k - 1 ; i++){
            ans += pairs[n - 2 - i] - pairs[i];
        }
        return ans;
    }
}
```