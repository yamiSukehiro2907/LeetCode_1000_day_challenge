LINK:- https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i


## Approach
- Intuition: When we look at the problem then the simple thing which comes to our mind is that whether to choose a number into the subseqence or not
- The condition of choosing a number into the subsequence is that the adjacent groups[i - 1] != groups[i] && groups[i] != groups[i + 1]
- Whether to start from 0 to 1 we will be just taking the alternate numbers on each index (The longest subsequence will always start from start as we just have to alternate and the main thing is that it is subsequence so indexing does not matter...)

