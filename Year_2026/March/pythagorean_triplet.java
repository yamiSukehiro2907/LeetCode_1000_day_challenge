package March;

public class pythagorean_triplet {
    static void main() {

    }

    static class Solution {
        boolean pythagoreanTriplet(int[] arr) {
            int maxElement = arr[0];
            for (int num : arr) maxElement = Math.max(num, maxElement);
            boolean[] present = new boolean[maxElement + 1];
            for (int num : arr) present[num] = true;
            for (int a = 1; a < maxElement; a++) {
                if (!present[a]) continue;
                for (int b = a + 1; b <= maxElement; b++) {
                    if (!present[b]) continue;
                    int A = a * a;
                    int B = b * b;
                    int sum = A + B;
                    if (isPerfectSquare(sum)) {
                        int C = (int) (Math.sqrt(sum));
                        if (C <= maxElement && present[C]) return true;
                    }
                }
            }
            return false;
        }

        boolean isPerfectSquare(int num) {
            return num == ((int) Math.sqrt(num) * (int) Math.sqrt(num));
        }
    }
}
