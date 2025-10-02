public class L_2 {
    public static void main(String[] args) {
        
    }

    private static class Solution {
        public int maxBottlesDrunk(int numBottles, int numExchange) {
            int totalDrink = 0;
            int filledBottles = numBottles;
            int emptyBottles = 0;
            int exchangeRate = numExchange;
            while (true) {
                if (emptyBottles >= exchangeRate) {
                    emptyBottles -= exchangeRate;
                    exchangeRate++;
                    filledBottles++;
                } else {
                    totalDrink += filledBottles;
                    emptyBottles += filledBottles;
                    filledBottles = 0;
                }
                if (filledBottles == 0 && emptyBottles < exchangeRate) {
                    break;
                }
            }
            return totalDrink;
        }
    }
}