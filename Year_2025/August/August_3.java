public class August_3 {
    public static void main(String[] args) {

    }
    @SuppressWarnings("unused")
    static class Solution {
        public int maxTotalFruits(int[][] fruits, int start, int k) {
            int n = fruits.length;
            int left = lowerBound(fruits, start - k);
            int right = left;
            int sum = 0;
            for (; right < n && fruits[right][0] <= start; right++) sum += fruits[right][1];
            int end = sum;
            for (; right < n && fruits[right][0] <= start + k; right++) {
                sum += fruits[right][1];
                while ((fruits[right][0] - start + fruits[right][0] - fruits[left][0]) > k && (start - fruits[left][0] + fruits[right][0] - fruits[left][0]) > k) {
                    sum -= fruits[left][1];
                    left++;
                }
                end = Math.max(end, sum);
            }
            return end;
        }

        private int lowerBound(int[][] arr, int target) {
            int low = 0, high = arr.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid][0] < target) low = mid + 1;
                else high = mid;
            }
            return low;
        }
    }
}