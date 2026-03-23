package March;

import java.util.Arrays;
import java.util.Stack;

public class sum_of_subarray_minimums {
    static void main() {
        int[] arr = {1, 7, 6, 1, 2, 10, 2};
        Solution sol = new Solution();
        System.out.println(sol.sumSubMins(arr));
    }

    static class Solution {
        public int sumSubMins(int[] arr) {
            int n = arr.length, totalSum = 0;
            int[] leftMin = new int[n];
            int[] rightMin = new int[n];
            Arrays.fill(leftMin, -1);
            Arrays.fill(rightMin, n);
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) stack.pop();
                if (!stack.isEmpty()) leftMin[i] = stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) stack.pop();
                if (!stack.isEmpty()) rightMin[i] = stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < n; i++) {
                int elementsInRight = rightMin[i] - i;
                int elementsInLeft = i - leftMin[i];
                totalSum += elementsInLeft * elementsInRight * arr[i];
            }
            return totalSum;
        }
    }
}
