import java.util.Arrays;

public class L_21 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public int countPalindromicSubsequence(String s) {
            int n = s.length(), count = 0;
            int[][] indexes = new int[26][2];
            for (int[] index : indexes) Arrays.fill(index, -1);
            int[][] prefix = new int[n][26];
            prefix[0][s.charAt(0) - 'a'] = 1;
            indexes[s.charAt(0) - 'a'][0] = 0;
            for (int i = 1; i < n; i++) {
                char ch = s.charAt(i);
                if (indexes[ch - 'a'][0] == -1) indexes[ch - 'a'][0] = i;
                else indexes[ch - 'a'][1] = i;
                System.arraycopy(prefix[i - 1], 0, prefix[i], 0, 26);
                prefix[i][ch - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (indexes[i][0] == -1 || indexes[i][1] == -1) continue;
                for (int j = 0; j < 26; j++) if (prefix[indexes[i][1] - 1][j] - prefix[indexes[i][0]][j] > 0) count++;
            }
            return count;
        }
    }
}
