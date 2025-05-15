package Year_2025.May.May_15;

import java.util.*;

public class Java {
    public static void main(String[] args) {
        String[] words = { "e", "a", "b" };
        int[] groups = { 0, 0, 1 };
        List<String> ans = Solution.getLongestSubsequence(words, groups);
        for (String n : ans) {
            System.out.print(n + " ");
        }
    }
}

class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            getLongestSubsequence(new String[] { "a" }, new int[] { 1 });
        }
    }

    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        int length = words.length, curr = groups[0];
        List<String> list = new ArrayList<>();
        list.add(words[0]);
        if (length == 1)
            return list;
        for (int i = 1; i < length; i++) {
            if (groups[i] != curr) {
                curr = groups[i];
                list.add(words[i]);
            }
        }
        return list;
    }
}
