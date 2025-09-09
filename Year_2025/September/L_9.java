public class L_9 {
    public static void main(String[] args) {

    }

    private static class Solution {
        static {
            System.gc();
            for (int i = 0; i < 500; i++) {
                peopleAwareOfSecret(1, 1, 1);
            }
        }
        private static int MOD = (int) 1e9 + 7;

        public static int peopleAwareOfSecret(int n, int delay, int forget) {
            if (n == 1)
                return 1;
            long sharedTo = 0;
            long[] dp = new long[n + 1];
            dp[1] = 1;
            for (int day = 2; day <= n; day++) {
                if (day - delay > 0)
                    sharedTo = (sharedTo + dp[day - delay] + MOD) % MOD;
                if (day - forget > 0)
                    sharedTo = (sharedTo - dp[day - forget] + MOD) % MOD;
                dp[day] = sharedTo;
            }
            long known = 0;
            for (int day = n - forget + 1; day <= n; day++) {
                known = (known + dp[day] + MOD) % MOD;
            }
            return (int) (known % MOD);
        }
    }
    /*
    class Solution {
    private int MOD = (int)1e9 + 7;
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        for(int i = 2 ; i <= n ; i++){
            int size = queue.size();
            for(int j = 0 ; j < size ; j++){
                int dayToKnowSecret = queue.poll();
                int dayDiff = i - dayToKnowSecret;
                if(dayDiff >= forget) continue;
                else {
                    queue.add(dayToKnowSecret);
                    if(dayDiff >= delay) queue.add(i);
                }
            }
        }
        return queue.size() % MOD;
    }
}
     */
}
