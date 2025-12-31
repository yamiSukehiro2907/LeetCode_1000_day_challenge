public class GFG_14 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] gas = {};
        int[] cost = {};
        System.out.println(solution.startStation(gas, cost));
    }

    private static class Solution {
        public int startStation(int[] gas, int[] cost) {
            int prev = -1;
            int cummulative = 0;
            for (int i = 0; i < gas.length; i++) {
                int diff = gas[i] - cost[i];
                if (diff < 0 && prev == -1)
                    continue;
                if (diff >= 0 && prev == -1) {
                    prev = i;
                    cummulative = diff;
                } else
                    cummulative += diff;
                if (cummulative < 0)
                    prev = -1;
            }
            if (cummulative < 0 || prev == -1)
                return -1;
            for (int i = 0; i < prev; i++) {
                int diff = gas[i] - cost[i];
                cummulative += (diff);
            }
            return cummulative < 0 ? -1 : prev;
        }
    }
}