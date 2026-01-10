package January;

public class substrings_with_k_distinct {

    static void main() {
        Solution s = new Solution();
        System.out.println(s.countSubstr("abc", 2));
    }

    static class Solution {
        public int countSubstr(String s, int k) {
            return solve(s, k) - solve(s, k - 1);
        }

        private int solve(String s, int k) {
            int[] freq = new int[26];
            int left = 0, count = 0, unique = 0;
            for (int right = 0; right < s.length(); right++) {
                if (freq[s.charAt(right) - 'a']++ == 0) unique++;
                while (unique > k) if (--freq[s.charAt(left++) - 'a'] == 0) unique--;
                count += right - left + 1;
            }
            return count;
        }
    }
}