package Year_2025.May.May_5;

public class Java {

    public static void main(String[] args) {

    }

    static class Solution {
        static {
            for (int i = 0; i < 500; i++)
                numEquivDominoPairs(new int[][] { { 1, 1 } });
        }

        public static int numEquivDominoPairs(int[][] dominoes) {
            if (dominoes.length <= 1)
                return 0;
            int freq[][] = new int[10][10];
            int totalPair = 0;
            for (int[] pair : dominoes) {
                int i = pair[0], j = pair[1];
                if (freq[i][j] > 0) {
                    totalPair += freq[i][j];
                    freq[i][j]++;
                } else if (freq[j][i] > 0) {
                    totalPair += freq[j][i];
                    freq[j][i]++;
                } else {
                    freq[i][j]++;
                }
            }
            return totalPair;
        }
    }
}