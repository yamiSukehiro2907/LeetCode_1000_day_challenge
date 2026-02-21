package February;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class special_binary_string {
    static void main() {
        String str = "10";
        Solution sol = new Solution();
        System.out.println(sol.makeLargestSpecial(str));
    }

    static class Solution {
        public String makeLargestSpecial(String s) {
            List<String> specialStrings = new ArrayList<>();
            int ones = 0, start = 0;
            for (int end = 0; end < s.length(); end++) {
                char ch = s.charAt(end);
                if (ch == '1') ones++;
                else ones--;
                if (ones == 0) {
                    String fixed = makeLargestSpecial(s.substring(start + 1, end));
                    specialStrings.add("1" + fixed + "0");
                    start = end + 1;
                }
            }
            specialStrings.sort(Collections.reverseOrder());
            StringBuilder sb = new StringBuilder();
            for (String temp : specialStrings) sb.append(temp);
            return sb.toString();
        }
    }
}
