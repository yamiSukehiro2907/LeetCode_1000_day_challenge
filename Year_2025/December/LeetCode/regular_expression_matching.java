package December.LeetCode;

public class regular_expression_matching {
    static void main() {

    }

    static class Solution {
        private Boolean[][] matched;
        private String string;
        private String pattern;

        public boolean isMatch(String s, String p) {
            this.string = s;
            this.pattern = p;
            int sLength = string.length(), pLength = pattern.length();
            this.matched = new Boolean[sLength + 1][pLength + 1];
            return find(sLength, pLength);
        }

        private boolean find(int sIndex, int pIndex) {
            if (pIndex == 0) return sIndex == 0;
            if (matched[sIndex][pIndex] != null) return matched[sIndex][pIndex];
            boolean canBeMade = false;
            char pChar = pattern.charAt(pIndex - 1);
            if (pChar == '*') {
                char prevChar = pattern.charAt(pIndex - 2);
                canBeMade = find(sIndex, pIndex - 2); // do not consume
                if (!canBeMade && sIndex > 0) {
                    char sChar = string.charAt(sIndex - 1);
                    if (prevChar == sChar || prevChar == '.') canBeMade = find(sIndex - 1, pIndex); // consume
                }
            } else {
                if (sIndex > 0) {
                    char sChar = string.charAt(sIndex - 1);
                    if (pChar == sChar || pChar == '.') canBeMade = find(sIndex - 1, pIndex - 1);
                    // if characters are equal or wildcard
                }
            }
            return matched[sIndex][pIndex] = canBeMade;
        }
    }
}
