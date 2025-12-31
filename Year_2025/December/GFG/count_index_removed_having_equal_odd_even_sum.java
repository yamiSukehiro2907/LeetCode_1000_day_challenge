package December.GFG;

import java.util.Arrays;

public class count_index_removed_having_equal_odd_even_sum {
    static void main() {
        int[] arr = {2 , 1 , 6 , 4};
        Solution sol = new Solution();
        System.out.println(sol.cntWays(arr));
    }

    static class Solution {
        public int cntWays(int[] arr) {
            int n = arr.length;
            int[] odd = new int[n];
            int[] even = new int[n];
            even[0] = arr[0];
            for (int i = 1; i < n; i++) {
                even[i] = even[i - 1];
                odd[i] = odd[i - 1];
                if (i % 2 == 0) even[i] += arr[i];
                else odd[i] += arr[i];
            }
            System.out.println("Odd: " + Arrays.toString(odd));
            System.out.println("Even: " + Arrays.toString(even));
            int count = 0;
            for (int i = 0; i < n; i++) {
                int leftOddSum = (i > 0) ? odd[i - 1] : 0;
                int leftEvenSum = (i > 0) ? even[i - 1] : 0;
                int rightEvenSum = (i > 0) ? odd[n - 1] - odd[i] : odd[n - 1];
                int rightOddSum = (i > 0) ? even[n - 1] - even[i] : even[n - 1];
                System.out.println("Index: " + i);
                System.out.println("leftOddSum: " + leftOddSum);
                System.out.println("leftEvenSum: " + leftEvenSum);
                System.out.println("rightOddSum: " + rightOddSum);
                System.out.println("rightEvenSum: " + rightEvenSum);
                if (leftOddSum + rightOddSum == leftEvenSum + rightEvenSum) count++;
            }
            return count;
        }
    }

}