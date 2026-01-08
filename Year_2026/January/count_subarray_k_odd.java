package January;

public class count_subarray_k_odd {

    static void main() {
        int[] arr = {2, 5, 6, 9};
        int k = 2;
        Solution obj = new Solution();
        System.out.println(obj.countSubarrays(arr, k));
    }

    static class Solution {
        public int countSubarrays(int[] arr, int k) {
            return atMostK(arr, k) - atMostK(arr, k - 1);
        }

        private int atMostK(int[] arr, int k) {
            if (k < 0) return 0;
            int count = 0, oddCount = 0, left = 0;
            for (int right = 0; right < arr.length; right++) {
                if (arr[right] % 2 == 1) oddCount++;
                while (oddCount > k) {
                    if (arr[left] % 2 == 1) oddCount--;
                    left++;
                }
                count += (right - left + 1);
            }
            return count;
        }
    }


}