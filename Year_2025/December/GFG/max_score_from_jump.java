package December.GFG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class max_score_from_jump {
    static void main(String[] args) {
        String s = "emhtmmzoechi";
        char[][] jumps = {
                {'c', 'z'}, {'e', 'h'}, {'e', 'z'}, {'c', 'm'},
                {'e', 't'}, {'h', 'z'}, {'i', 't'}
        };
        Solution sol = new Solution();
        System.out.println("Result: " + sol.maxScore(s, jumps));
    }

    private static class Solution2 {
        public long maxScore(String s, char[][] jumps) {
            int n = s.length();
            List<List<Integer>> validSources = new ArrayList<>();
            for (int i = 0; i < 26; i++) validSources.add(new ArrayList<>());
            for (char[] jump : jumps) validSources.get(jump[1] - 'a').add(jump[0] - 'a');
            for (int i = 0; i < 26; i++) {
                if (!validSources.get(i).contains(i)) validSources.get(i).add(i);
            }

            long[] totalPrefix = new long[n];
            long[][] charPrefix = new long[26][n];

            int firstChar = s.charAt(0) - 'a';
            totalPrefix[0] = s.charAt(0);
            charPrefix[firstChar][0] = s.charAt(0);

            for (int i = 1; i < n; i++) {
                int c = s.charAt(i) - 'a';
                totalPrefix[i] = totalPrefix[i - 1] + s.charAt(i);
                for (int k = 0; k < 26; k++) charPrefix[k][i] = charPrefix[k][i - 1];
                charPrefix[c][i] += s.charAt(i);
            }

            long[][] bestPrevious = new long[26][26];
            for (long[] row : bestPrevious) Arrays.fill(row, Long.MIN_VALUE);

            long[] dp = new long[n];
            Arrays.fill(dp, Long.MIN_VALUE);
            dp[0] = 0;

            updateBestPrevious(0, s, totalPrefix, charPrefix, dp, bestPrevious);

            long globalMax = 0;

            for (int i = 1; i < n; i++) {
                int u = s.charAt(i) - 'a';
                long currentPart = totalPrefix[i - 1] - charPrefix[u][i - 1];

                for (int v : validSources.get(u)) {
                    long prevVal = bestPrevious[v][u];
                    if (prevVal != Long.MIN_VALUE) {
                        dp[i] = Math.max(dp[i], currentPart + prevVal);
                    }
                }

                if (dp[i] != Long.MIN_VALUE) {
                    globalMax = Math.max(globalMax, dp[i]);
                    updateBestPrevious(i, s, totalPrefix, charPrefix, dp, bestPrevious);
                }
            }

            return globalMax;
        }

        private void updateBestPrevious(int i, String s, long[] totalPrefix, long[][] charPrefix, long[] dp, long[][] bestPrevious) {
            int sourceChar = s.charAt(i) - 'a';
            long prevTotal = (i == 0) ? 0 : totalPrefix[i - 1];

            for (int targetChar = 0; targetChar < 26; targetChar++) {
                long prevChar = (i == 0) ? 0 : charPrefix[targetChar][i - 1];
                long val = dp[i] - prevTotal + prevChar;
                bestPrevious[sourceChar][targetChar] = Math.max(bestPrevious[sourceChar][targetChar], val);
            }
        }
    }


    private static class Solution {
        private int[][] prefix;
        private int[] totalPrefix;
        private String str;
        private List<int[]> pairs;
        private List<List<Integer>> charIndexes;

        public long maxScore(String s, char[][] jumps) {
            this.str = s;
            int n = str.length();
            this.totalPrefix = new int[n];
            this.pairs = new ArrayList<>();
            this.charIndexes = new ArrayList<>();
            this.prefix = new int[n][26];
            fillCharIndexes();
            fillPrefixes();
            fillSameStartEndPairs();
            fillJumpPairs(jumps);
            return findBestScoreDP(n);
        }

        private long findBestScoreDP(int n) {
            long[] dp = new long[n];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            pairs.sort(Comparator.comparingInt(a -> a[0]));
            for (int[] pair : pairs) {
                int u = pair[0];
                int v = pair[1];
                int w = pair[2];
                if (dp[u] != -1) {
                    if (dp[u] + w > dp[v]) {
                        dp[v] = dp[u] + w;
                    }
                }
            }
            long maxScore = 0;
            for (long score : dp) {
                maxScore = Math.max(maxScore, score);
            }
            return maxScore;
        }

        private void fillCharIndexes() {
            for(int i = 0; i < 26; i++) charIndexes.add(new ArrayList<>());
            for (int i = 0; i < str.length(); i++) {
                charIndexes.get(str.charAt(i) - 'a').add(i);
            }
        }

        private void fillPrefixes() {
            totalPrefix[0] = str.charAt(0);
            prefix[0][str.charAt(0) - 'a'] = str.charAt(0);
            for (int i = 1; i < str.length(); i++) {
                System.arraycopy(prefix[i - 1], 0, prefix[i], 0, 26);
                totalPrefix[i] = totalPrefix[i - 1] + str.charAt(i);
                prefix[i][str.charAt(i) - 'a'] += str.charAt(i);
            }
        }

        private void fillSameStartEndPairs() {
            for(int i = 0; i < 26; i++){
                List<Integer> indexes = charIndexes.get(i);
                if(indexes.size() > 1){
                    for(int s = 0; s < indexes.size() - 1; s++){
                        for(int e = s + 1; e < indexes.size(); e++){
                            int startIndex = indexes.get(s);
                            int endIndex = indexes.get(e);
                            if(startIndex >= endIndex) continue;
                            int score = getScore(startIndex, endIndex);
                            if(score >= 0) pairs.add(new int[]{startIndex, endIndex, score});
                        }
                    }
                }
            }
        }

        private void fillJumpPairs(char[][] jumps){
            for(char[] jump : jumps){
                int from = jump[0] - 'a', to = jump[1] - 'a';
                for(int start : charIndexes.get(from)){
                    for(int end : charIndexes.get(to)){
                        if(start >= end) continue;
                        int score = getScore(start, end);
                        if(score >= 0) pairs.add(new int[]{start, end, score});
                    }
                }
            }
        }

        private int getScore(int startIndex, int endIndex) {
            int leftPrefix = (startIndex > 0) ? totalPrefix[startIndex - 1] : 0;
            int rightPrefix = totalPrefix[endIndex - 1];
            int score = rightPrefix - leftPrefix;
            char ch = str.charAt(endIndex);
            int sameLetterLeftPrefix = (startIndex > 0) ? prefix[startIndex - 1][ch - 'a'] : 0;
            int sameLetterRightPrefix = prefix[endIndex - 1][ch - 'a'];
            int sameLetterTotalPrefix = (sameLetterRightPrefix - sameLetterLeftPrefix);
            score -= sameLetterTotalPrefix;
            return score;
        }
    }
}