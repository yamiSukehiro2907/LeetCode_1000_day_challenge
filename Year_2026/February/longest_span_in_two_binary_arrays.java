package February;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class longest_span_in_two_binary_arrays {
    static void main() {
        int[] a1 = {1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1};
        int[] a2 = {1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0};
        System.out.println("A1: " + Arrays.toString(a1));
        System.out.println("A2: " + Arrays.toString(a2));
        Solution s = new Solution();
        System.out.println(s.equalSumSpan(a1, a2));
    }

    static class Solution {
        public int equalSumSpan(int[] a1, int[] a2) {
            if (a1.length != a2.length || a1.length == 0) return 0;
            Map<Integer, Integer> map = new HashMap<>();
            int maxLength = 0, diff = 0;
            for (int i = 0; i < a1.length; i++) {
                diff += (a1[i] - a2[i]);
                if (diff == 0) maxLength = i + 1;
                else if (map.containsKey(diff)) maxLength = Math.max(maxLength, i - map.get(diff));
                else map.put(diff, i);
            }
            return maxLength;
        }
    }
}


