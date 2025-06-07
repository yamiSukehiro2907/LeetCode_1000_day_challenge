package May.May_25;

import java.util.HashMap;
import java.util.Map;

public class Approach2 {
    static class Solution {
        public int longestPalindrome(String[] words) {
            int n = words.length, ans = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                char ch1 = words[i].charAt(0);
                char ch2 = words[i].charAt(1);
                String rev = Character.toString(ch2) + Character.toString(ch1);
                if (map.containsKey(rev)) {
                    map.put(rev, map.get(rev) - 1);
                    ans += 4;
                    if (map.get(rev) == 0) {
                        map.remove(rev);
                    }
                } else {
                    map.put(words[i], map.getOrDefault(words[i], 0) + 1);
                }
            }
            for (String key : map.keySet()) {
                char ch1 = key.charAt(0);
                char ch2 = key.charAt(1);
                if (ch1 == ch2) {
                    ans += 2;
                    return ans;
                }
            }
            return ans;
        }
    }
}
