package February;

import java.util.Arrays;

public class isomorphic_strings {
    static void main() {

    }

    static class Solution {
        public boolean areIsomorphic(String s1, String s2) {
            if (s1.length() != s2.length()) return false;

            char[] freqAB = new char[26];
            char[] freqBA = new char[26];

            Arrays.fill(freqAB, '.');
            Arrays.fill(freqBA, '.');

            for (int i = 0; i < s1.length(); i++) {
                char ch1 = s1.charAt(i), ch2 = s2.charAt(i);

                if (freqAB[ch1 - 'a'] != '.' && freqAB[ch1 - 'a'] != ch2) return false;
                else freqAB[ch1 - 'a'] = ch2;

                if (freqBA[ch2 - 'a'] != '.' && freqBA[ch2 - 'a'] != ch1) return false;
                else freqBA[ch2 - 'a'] = ch1;
            }
            return true;
        }
    }
}
