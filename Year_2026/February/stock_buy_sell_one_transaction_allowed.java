package February;

public class stock_buy_sell_one_transaction_allowed {

    static void main() {

    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int totalProfit = 0;
            int minPrice = Integer.MAX_VALUE;
            int maxPrice = 0;
            for (int price : prices) {
                if (price < minPrice) {
                    minPrice = price;
                    maxPrice = 0;
                } else if (price > maxPrice) {
                    maxPrice = price;
                    int profit = maxPrice - minPrice;
                    if (profit > totalProfit) totalProfit = profit;
                }
            }
            return totalProfit;
        }
    }
}
