public class August_5 {
    public static void main(String[] args) {

    }
    @SuppressWarnings("unused")
    static class Solution {
        public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
            int count = 0;
            for (int fruit : fruits) {
                for (int i = 0; i < baskets.length; i++) {
                    if (fruit <= baskets[i]) {
                        count++;
                        baskets[i] = 0;
                        break;
                    }
                }
            }
            return fruits.length - count;
        }
    }
}