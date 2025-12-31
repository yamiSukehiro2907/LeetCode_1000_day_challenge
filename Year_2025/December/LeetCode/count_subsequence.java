package December.LeetCode;

import java.util.Arrays;

public class count_subsequence {
    static void main() {
        Solution solution = new Solution();
        String s1 = "rabbbit";
        String s2 = "rabbit";
        System.out.println(solution.numDistinct(s1, s2));
    }

    private static class Solution {
        public int numDistinct(String s1, String s2) {
            ///  subsequence in s1 to create s2
            ///  if current char matches then we have two possibilities
            ///  either choose that character or skip that character and check for possibility
            int[][] sequence = new int[s1.length() + 1][s2.length() + 1];
            for(int[] temp : sequence) Arrays.fill(temp, -1);
            sequence[0][0] = 1;
            return find(s1, s2, s1.length(), s2.length(), sequence);
        }

        private int find(String s1, String s2, int s1Index, int s2Index, int[][] sequence) {
            if(s2Index == 0) return 1;
            if(s1Index == 0) return 0;
            if (sequence[s1Index][s2Index] != -1) return sequence[s1Index][s2Index];
            int totalWays = 0;
            char s1Char = s1.charAt(s1Index - 1) , s2Char = s2.charAt(s2Index - 1);
            if(s1Char == s2Char) totalWays += find(s1, s2, s1Index - 1, s2Index - 1, sequence);
            totalWays += find(s1, s2, s1Index - 1, s2Index, sequence);
            return sequence[s1Index][s2Index] = totalWays;
        }
    }

}
