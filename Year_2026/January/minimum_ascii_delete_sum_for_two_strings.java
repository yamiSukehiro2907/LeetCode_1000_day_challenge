package January;

public class minimum_ascii_delete_sum_for_two_strings {
    static void main() {
        Solution s = new Solution();
        System.out.println(s.minimumDeleteSum("delete" , "leet"));
    }

    static class Solution {
        public int minimumDeleteSum(String s1, String s2) {
            byte[] s1Arr = s1.getBytes();
            byte[] s2Arr = s2.getBytes();
            int m = s1Arr.length;
            int n = s2Arr.length;
            int[] prev = new int[m + 1];
            int[] curr = new int[m + 1];
            for (int i = 1; i <= m; i++) prev[i] = prev[i - 1] + s1Arr[i - 1];
            for (int j = 1; j <= n; j++) {
                curr[0] = prev[0] + s2Arr[j - 1]; // cost of removing all characters from s2Arr to j
                for (int i = 1; i <= m; i++) {
                    if (s1Arr[i - 1] == s2Arr[j - 1]) curr[i] = prev[i - 1]; // no deletion needed
                    else curr[i] = Math.min(curr[i - 1] + s1Arr[i - 1], prev[i] + s2Arr[j - 1]); // either delete s1 or s2 char
                }
                int[] temp = prev;
                prev = curr;
                curr = temp;
            }
            return prev[m];
        }
    }
}

