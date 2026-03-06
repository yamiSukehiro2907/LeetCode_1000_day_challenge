package March;

public class smallest_window_containing_all_characters {
    static void main() {
        String s = "zoom";
        String p = "zooe";
        System.out.println(Solution.minWindow(s, p));
    }

    static class Solution {
        private static int[] pFreqArr;

        public static String minWindow(String s, String p) {
            pFreqArr = new int[26];
            for (char ch : p.toCharArray()) pFreqArr[ch - 'a']++;
            int[] freq = new int[26];
            int left = -1;
            int start = -1, end = -1;
            for (int right = 0; right < s.length(); right++) {
                freq[s.charAt(right) - 'a']++;
                if (isValid(freq)) {
                    while (isValid(freq)) {
                        left++;
                        if (start == -1 || end - start + 1 > right - left + 1) {
                            start = left;
                            end = right;
                        }
                        freq[s.charAt(left) - 'a']--;
                    }
                }
            }
            if (start == -1) return "";
            return s.substring(start, end + 1);
        }

        private static boolean isValid(int[] freq) {
            for (int i = 0; i < 26; i++) if (freq[i] < pFreqArr[i]) return false;
            return true;
        }
    }
}
