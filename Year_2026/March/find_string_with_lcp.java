package March;

public class find_string_with_lcp {
    static void main() {

    }

    static class Solution {
        public String findTheString(int[][] lcp) {
            int n = lcp.length;
            char[] word = new char[n];
            int current = 0;
            for (int i = 0; i < n; i++) {
                if (word[i] == '\0') {
                    if (current > 25) return "";
                    word[i] = (char) (current + 'a');
                    for (int j = i + 1; j < n; j++)
                        if (lcp[i][j] > 0) word[j] = word[i]; // if you have common then same char starts
                    current++;
                }
            }
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (word[i] != word[j]) {
                        if (lcp[i][j] != 0) return "";
                    } // different character should have no common prefix
                    else { // same character
                        if (i == n - 1 || j == n - 1) {
                            if (lcp[i][j] != 1) return "";
                        } // if last character then it must have 1 common char
                        else if (lcp[i][j] - 1 != lcp[i + 1][j + 1])
                            return ""; // character same then next characters should have common by -1
                    }
                }
            }
            return new String(word);
        }
    }

}
