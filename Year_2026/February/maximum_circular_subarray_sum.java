package February;


public class maximum_circular_subarray_sum {
    static void main() {

    }

    static class Solution {
        public int maxCircularSum(int[] arr) {
            int maxSum = Integer.MIN_VALUE;
            int currentSum = 0;
            for (int num : arr) {
                currentSum += num;
                maxSum = Math.max(currentSum, maxSum);
                if (currentSum < 0) currentSum = 0;
            }
            currentSum = 0;
            int minStartIndex = -1, minEndIndex = -1;
            int sIndex = -1;
            int minSum = 0;
            for (int i = 0; i < arr.length; i++) {
                currentSum += arr[i];
                if (currentSum < minSum) {
                    minStartIndex = sIndex;
                    minEndIndex = i;
                    minSum = currentSum;
                }
                if (currentSum >= 0) {
                    sIndex = i + 1;
                    currentSum = 0;
                }
            }
            if (minStartIndex != -1 && minEndIndex != -1) {
                int sum = 0;
                for (int i = 0; i < minStartIndex; i++) sum += arr[i];
                for (int i = minEndIndex + 1; i < arr.length; i++) sum += arr[i];
                maxSum = Math.max(maxSum, sum);
            }
            return maxSum;
        }
    }

    static class Solution2 {
        public int maxCircularSum(int[] arr) {
            int maxSum = arr[0], minSum = arr[0];
            int currentMinSum = 0, currentMaxSum = 0, totalSum = 0;
            for(int num : arr) {
                currentMinSum += num;
                currentMaxSum += num;
                totalSum += num;
                maxSum = Math.max(currentMaxSum, maxSum);
                minSum = Math.min(currentMinSum, minSum);
                if(currentMinSum > 0) currentMinSum = 0;
                if(currentMaxSum < 0) currentMaxSum = 0;
            }
            if(totalSum == minSum) return maxSum;
            return Math.max(totalSum - minSum, maxSum);
        }
    }
}
