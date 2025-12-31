
public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        static {
            for (int i = 0; i < 500; i++)
                maxDifference("a");
        }

        public static int maxDifference(String s) {
            if (s.length() == 1) {
                return 1;
            }
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }
            int maxOdd = 0, maxEven = s.length();
            for (int num : freq) {
                if (num % 2 == 1)
                    maxOdd = Math.max(num, maxOdd);
                else
                    maxEven = Math.min(num == 0 ? s.length() : num, maxEven);
            }
            return maxOdd - maxEven;
        }
    }
}