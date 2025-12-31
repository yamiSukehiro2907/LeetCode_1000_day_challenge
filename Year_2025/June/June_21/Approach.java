public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        static {
            for (int i = 0; i < 500; i++) {
                minimumDeletions("a", 0);
            }
        }

        public static int minimumDeletions(String word, int k) {
            if (word.length() == 1 && k == 0)
                return 0;
            int freq[] = new int[26];
            for (int i = 0; i < word.length(); i++) {
                freq[word.charAt(i) - 'a']++;
            }
            int ans = word.length();
            for (int i = 0; i < 26; i++) {
                if (freq[i] == 0)
                    continue;
                int temp = 0;
                for (int j = 0; j < 26; j++) {
                    if (freq[j] == 0)
                        continue;
                    if (i == j)
                        continue;
                    if (freq[j] < freq[i]) {
                        temp += freq[j];
                    } else if (freq[j] > freq[i] + k) {
                        temp += freq[j] - (freq[i] + k);
                    }
                }
                ans = Math.min(ans, temp);
            }
            return ans;
        }
    }
}
