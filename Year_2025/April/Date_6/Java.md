https://leetcode.com/problems/largest-divisible-subset/description/
```
class Solution {
    public List<Integer> largestDivisibleSubset(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] subset = new int[n];
        int[] indexes = new int[n];
        int maxIndex = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
            subset[i] = 1;
            int limit = (arr[i] + 1) / 2;
            for (int j = 0; j < i && arr[j] <= limit; j++) {
                if (arr[i] % arr[j] == 0 && subset[j] + 1 > subset[i]) {
                    subset[i] = 1 + subset[j];
                    indexes[i] = j;
                }
            }
            maxIndex = subset[i] > subset[maxIndex] ? i : maxIndex;
        }
        while (maxIndex != indexes[maxIndex]) {
            ans.add(arr[maxIndex]);
            maxIndex = indexes[maxIndex];
        }
        ans.add(arr[maxIndex]);
        Collections.reverse(ans);
        return ans;
    }
}
```