package February;

import java.util.HashMap;
import java.util.Map;

public class longest_subarray_with_majority_greater_than_k {
    static void main() {
        int[] arr = {1, 2, 3, 4, 1};
        int k = 2;
        Solution s = new Solution();
        System.out.println(s.longestSubarray(arr, k));
    }

    static class Solution {
        public int longestSubarray(int[] arr, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            int diff = 0, maxLength = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > k) diff++;
                else diff--;
                if (diff > 0) maxLength = i + 1;
                if (map.containsKey(diff - 1)) {
                    maxLength = Math.max(maxLength, i - map.get(diff - 1));
                }
                if (!map.containsKey(diff)) map.put(diff, i);
            }
            return maxLength;
        }
    }
}
