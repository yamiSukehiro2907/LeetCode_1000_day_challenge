
public class August_17 {
    public static void main(String[] args) {

    }

    @SuppressWarnings("unused")
    static class Solution {
        public double new21Game(int n, int k, int maxPts) {
            double[] dp = new double[k + 1];
            dp[k] = 1;
            double p = 1.0 / maxPts;
            double currAns = Math.min(n - k + 1, maxPts);
            currAns -= 1;
            for (int score = k - 1; score >= 0; --score) {
                currAns += dp[score + 1];
                dp[score] = currAns;
                dp[score] *= p;
                double elemToRem;
                if (score + maxPts > k) {
                    if (score + maxPts <= n) { // if the right end is less than n then 1
                        elemToRem = 1;
                    } else {
                        elemToRem = 0; // else 0
                    }
                } else {
                    // if the right end is less than k then remove right end of window
                    elemToRem = dp[score + maxPts];
                }
                currAns -= elemToRem;
            }
            return dp[0];
        }
    }
}
