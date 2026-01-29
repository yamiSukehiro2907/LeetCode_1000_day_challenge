package January;

import java.util.LinkedList;
import java.util.Queue;

public class stream_first_non_repeating {
    static void main() {
        String s = "aabb";
        Solution sol = new Solution();
        System.out.println(sol.firstNonRepeating(s));
    }

    static class Solution {
        public String firstNonRepeating(String s) {
            StringBuilder sb = new StringBuilder();
            int[] present = new int[26];
            Queue<Character> queue = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                queue.add(ch);
                present[ch - 'a']++;
                while (!queue.isEmpty() && present[queue.peek() - 'a'] > 1) queue.poll();
                sb.append(queue.isEmpty() ? '#' : queue.peek());
            }
            return sb.toString();
        }
    }
}
