package February;

public class longest_balanced_substring_one {
    static void main(String[] args) {
        String[] strings = {"abbac" , "zzabccy" , "aba" , "kl"};
        Solution solution = new Solution();
        for(String s : strings){
            System.out.println(solution.longestBalanced(s));
        }
    }

    static class Solution {
        private static final int[][] freq = new int[1001][26];
        private char[] arr;

        public int longestBalanced(String s) {
            this.arr = s.toCharArray();
            fillFreq();
            int maxLength = arr.length;
            while (maxLength > 1) {
                if (possible(maxLength)) return maxLength;
                maxLength--;
            }
            return 1;
        }

        private void fillFreq() {
            for (int j = 0; j < 26; j++) freq[0][j] = 0;
            freq[0][arr[0] - 'a']++;
            for (int i = 1; i < arr.length; i++) {
                for (int j = 0; j < 26; j++) freq[i][j] = freq[i - 1][j];
                char ch = arr[i];
                freq[i][ch - 'a']++;
            }
        }

        private boolean possible(int length) {
            for (int i = length; i <= arr.length; i++) {
                int count = -1;
                boolean possible = true;
                for (int j = 0; j < 26; j++) {
                    int diffCount = freq[i - 1][j] - ((i - length - 1 >= 0) ? freq[i - length - 1][j] : 0);
                    if (diffCount > 0) {
                        if (count == -1) count = diffCount;
                        else if (diffCount != count) {
                            possible = false;
                            break;
                        }
                    }
                }
                if (possible) return true;
            }
            return false;
        }
    }
}