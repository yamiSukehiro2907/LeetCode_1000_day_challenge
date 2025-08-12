public class August_4 {
    public static void main(String[] args) {

    }

    @SuppressWarnings("unused")
    static class Solution {
        static {
            for (int i = 0; i < 300; i++) {
                totalFruit(new int[0]);
            }
        }

        public static int totalFruit(int[] fruits) {
            int ans = 0, n = fruits.length;
            int fruit1 = -1, fruit2 = -1;
            int c1 = 0, c2 = 0;
            int prev = -1, prevIndex = -1;
            for (int i = 0; i < n; i++) {
                int fruit = fruits[i];
                if (fruit1 == -1 || fruit == fruit1) {
                    fruit1 = fruit;
                    c1++;
                    if (prev != fruit1) {
                        prev = fruit1;
                        prevIndex = i;
                    }
                } else if (fruit2 == -1 || fruit2 == fruit) {
                    fruit2 = fruit;
                    c2++;
                    if (prev != fruit2) {
                        prev = fruit;
                        prevIndex = i;
                    }
                } else {
                    ans = Math.max(ans, c1 + c2);
                    if (prev == fruit1) {
                        c1 = i - prevIndex;
                        fruit2 = fruit;
                        c2 = 1;
                    } else {
                        c2 = i - prevIndex;
                        fruit1 = fruit;
                        c1 = 1;
                    }
                    prev = fruit;
                    prevIndex = i;
                }
            }
            return Math.max(ans, c1 + c2);
        }
    }
}
