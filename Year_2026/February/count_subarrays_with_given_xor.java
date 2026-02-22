package February;

import java.util.HashMap;
import java.util.Map;

public class count_subarrays_with_given_xor {
    static void main() {
        int k = 6;
        int[] arr = {4, 2, 2, 6, 4};
        Solution s = new Solution();
        System.out.println(s.subarrayXor(arr, k));
    }

    static class Solution {
        public long subarrayXor(int[] arr, int k) {
            long count = 0L;
            int xor = 0;
            Map<Integer, Long> map = new HashMap<>();
            for (int num : arr) {
                xor ^= num;
                count += map.getOrDefault(k ^ xor, 0L);
                if (xor == k) count++;
                map.put(xor, map.getOrDefault(xor, 0L) + 1);
            }
            return count;
        }
    }
}
