https://leetcode.com/problems/count-subarrays-with-score-less-than-k

## Count subarrays with score less than K

## Approach


- Score = (length of subarray) * (sum of subarray)
- Keep a left and right pointer of the subarrays and currsum
- If we get the score greater than k then keep removing left elements and move left pointer by 1
- For each iteration we will be adding the current subarray with score less than k 