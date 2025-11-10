
public class GFG_10 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(arr));
    }

    private static class Solution {

        public int maxProfit(int arr[]) {
            int buy = -arr[0], sell = 0, skip = 0;
            // B S C B S
            for (int i = 1; i < arr.length; i++) {
                int prevBuy = buy, prevSell = sell, prevSkip = skip;
                // new buying state
                buy = Math.max(prevBuy, prevSkip - arr[i]);
                // new selling state
                sell = prevBuy + arr[i];
                // new cooling state
                skip = Math.max(prevSkip, prevSell);
            }
            return Math.max(sell, skip);
        }
    }

}
