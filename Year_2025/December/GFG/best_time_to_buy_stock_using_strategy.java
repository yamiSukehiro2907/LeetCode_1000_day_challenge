package December.GFG;

import java.util.Arrays;

public class best_time_to_buy_stock_using_strategy {

    static void main() {
        int[] prices = {4, 2, 8};
        int[] strategy = {-1, 0, 1};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices, strategy, k));
    }

    static class Solution {
        public long maxProfit(int[] prices, int[] strategy, int k) {
            int n = prices.length;
            long[] priceSum = new long[n];
            long[] profitSum = new long[n];
            priceSum[0] = prices[0];
            profitSum[0] = (long) strategy[0] * prices[0];
            for (int i = 1; i < n; i++) {
                priceSum[i] = priceSum[i - 1] + prices[i];
                profitSum[i] = profitSum[i - 1] + (long) prices[i] * strategy[i];
            }
            System.out.println("PriceSum: " + Arrays.toString(priceSum));
            System.out.println("ProfitSum: " + Arrays.toString(profitSum));
            long maxProfit = profitSum[n - 1];
            for (int leftIndex = 0; leftIndex <= n - k; leftIndex++) {
                System.out.println("leftIndex: " + leftIndex + " rightIndex: " + leftIndex + k + " maxProfit: " + maxProfit);
                int rightIndex = leftIndex + k - 1;
                int middleIndex = (leftIndex + rightIndex) / 2;
                System.out.println("MiddleIndex: " + middleIndex);
                long leftOutSideWindowProfit = (leftIndex > 0) ? profitSum[leftIndex - 1] : 0;
                long rightOutSideWindowProfit = profitSum[n - 1] - profitSum[rightIndex];
                long windowProfit = priceSum[rightIndex] - priceSum[middleIndex];
                long profit = leftOutSideWindowProfit + rightOutSideWindowProfit + windowProfit;
                System.out.println("leftOutSideWindowProfit: " + leftOutSideWindowProfit + " rightOutSideWindowProfit: " + rightOutSideWindowProfit + " windowProfit: " + profit);
                System.out.println("CurrentProfit: " + profit);
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
            return maxProfit;
        }
    }
}
