package March;

public class longest_substring_with_k_uniques {
    static void main() {

    }

    static class Solution {
        public int longestKSubstr(String s, int k) {
            if (k == 26) return s.length();
            int[] freq = new int[26];
            char[] arr = s.toCharArray();
            int maxLength = -1, left = -1, uniqueCount = 0;
            for (int right = 0; right < arr.length; right++) {
                if (freq[arr[right] - 'a'] == 0) uniqueCount++;
                freq[arr[right] - 'a']++;
                while (uniqueCount > k) {
                    left++;
                    if (freq[arr[left] - 'a'] == 1) uniqueCount--;
                    freq[arr[left] - 'a']--;
                }
                if (uniqueCount == k) {
                    int length = right - left;
                    if (length > maxLength) maxLength = length;
                }
            }
            return maxLength;
        }
    }
}
