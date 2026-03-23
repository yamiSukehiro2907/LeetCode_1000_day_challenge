package March;

public class dice_throw {
    static void main() {

    }

    static class Solution {
        private static int faces;
        private static Integer[][] dp;

        static int noOfWays(int m, int n, int x) {
            faces = m;
            dp = new Integer[n + 1][x + 1];
            return find(n, x);
        }

        static int find(int dices, int sum) {
            if (dices == 0 && sum == 0) return 1;
            if (faces * dices < sum) return 0;
            if (dices <= 0 || sum <= 0) return 0;
            if (dp[dices][sum] != null) return dp[dices][sum];
            int totalWays = 0;
            for (int face = 1; face <= faces; face++) totalWays += find(dices - 1, sum - face);
            return dp[dices][sum] = totalWays;
        }
    }

    ;
}
