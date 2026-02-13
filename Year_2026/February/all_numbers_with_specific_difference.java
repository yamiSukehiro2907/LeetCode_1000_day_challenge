package February;

public class all_numbers_with_specific_difference {
    static void main() {

    }

    static class Solution {
        public int getCount(int n, int d) {
            int minNum = n + 1;
            int start = 1, end = n;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);
                if (isPossible(mid, d)) {
                    end = mid - 1;
                    minNum = mid;
                } else start = mid + 1;
            }
            return n - minNum + 1;
        }

        private boolean isPossible(int num, int d) {
            int digitSum = 0, temp = num;
            while (temp > 0) {
                int digit = temp % 10;
                digitSum += digit;
                temp /= 10;
            }
            return num - digitSum >= d;
        }
    }

    ;
}
