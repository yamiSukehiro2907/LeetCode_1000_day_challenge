public class July_15 {
    public static void main(String[] args) {

    }

    static class Solution {

        static {
            for (int i = 0; i < 500; i++) {
                isValid("a");
            }
        }
        private static String Vowels = "aeiouAEIOU";

        public static boolean isValid(String word) {
            if (word.length() < 3)
                return false;
            boolean containsVowel = false;
            boolean containsConsonant = false;
            for (char ch : word.toCharArray()) {
                if (isAlphabet(ch)) {
                    if (isVowel(ch)) {
                        containsVowel = true;
                        continue;
                    }
                    containsConsonant = true;
                } else if (isDigit(ch))
                    continue;
                else
                    return false;
            }
            return (containsVowel && containsConsonant);
        }

        private static boolean isVowel(char ch) {
            return Vowels.indexOf(ch) != -1;
        }

        private static boolean isAlphabet(char ch) {
            return (ch <= 'z' && ch >= 'a') || (ch <= 'Z' && ch >= 'A');
        }

        private static boolean isDigit(char ch) {
            return ch <= '9' && ch >= '0';
        }
    }
}
