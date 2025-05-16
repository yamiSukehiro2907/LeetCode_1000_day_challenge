package Year_2025.May.May_16;

import java.util.*;

public class Java {
    public static void main(String[] args) {
        String[] words = { "bdb", "aaa", "ada" };
        int[] groups = { 2, 1, 3 };
        new Solution().getWordsInLongestSubsequence(words, groups);
    }

    static class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        if (n != groups.length) return new ArrayList<>();
        Map<Long, List<Integer>> idx = new HashMap<>();
        int[] next = new int[n];
        int[] lens = new int[n];
        int best = 0;
        Arrays.fill(next, n);
        for (int i = n - 1; i >= 0; i--) {
            String w = words[i];
            int len = w.length();
            int max = 1;
            long mask = 0L;
            long[] bits = new long[len];
            for (int j = 0; j < len; j++) {
                mask |= bits[j] = (long)(w.charAt(j) - 'a' + 1) << (5 * j);
            }
            for (int j = 0; j < len; j++) {
                long key = mask ^ bits[j];
                List<Integer> matches = idx.computeIfAbsent(key, k -> new ArrayList<>());
                for (int pos : matches) {
                    if (max >= lens[pos] + 1 || groups[pos] == groups[i]) continue;
                    max = lens[pos] + 1;
                    next[i] = pos;
                }
                matches.add(i);
            }
            lens[i] = max;
            if (lens[best] < max) best = i;
        }
        List<String> res = new ArrayList<>(lens[best]);
        for (int i = best; i < n; i = next[i]) {
            res.add(words[i]);
        }
        return res;
    }
}
/*
class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        if (n != groups.length) return new ArrayList<>();
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dp.add(new ArrayList<>());
            dp.get(i).add(words[i]);
            for (int j = 0; j < i; j++) {
                if (groups[j] != groups[i] && valid(words[j], words[i]) && dp.get(j).size() + 1 > dp.get(i).size()) {
                    List<String> newList = new ArrayList<>(dp.get(j));
                    newList.add(words[i]);
                    dp.set(i, newList);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (List<String> temp : dp) {
            if (temp.size() > ans.size()) ans = temp;
        }
        return ans;
    }

    private boolean valid(String str1, String str2) {
        int hammingDistance = 0;
        int n1 = str1.length(), n2 = str2.length();
        if (n1 != n2) return false;
        for (int i = 0; i < n1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) hammingDistance++;
            if (hammingDistance > 1) return false;
        }
        return hammingDistance == 1;
    }
}
*/
}