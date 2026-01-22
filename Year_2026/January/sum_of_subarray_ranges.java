package January;

import java.util.Stack;

public class sum_of_subarray_ranges {
    static void main() {

    }

    static class Solution {
        public int subarrayRanges(int[] arr) {
            int[] leftMin = new int[arr.length]; // the count of elements in left where arr[i] is min
            int[] rightMin = new int[arr.length]; // the count of elements in right where arr[i] is min
            int[] leftMax = new int[arr.length]; // the count of elements in left where arr[i] is max
            int[] rightMax = new int[arr.length]; // the count of elements in right where arr[i] is max
            Stack<Integer> minStack = new Stack<>();
            Stack<Integer> maxStack = new Stack<>();
            for (int i = 0; i < arr.length; i++) {
                while (!minStack.isEmpty() && arr[minStack.peek()] >= arr[i]) minStack.pop();
                while (!maxStack.isEmpty() && arr[maxStack.peek()] <= arr[i]) maxStack.pop();
                leftMin[i] = (minStack.isEmpty()) ? i + 1 : i - minStack.peek();
                leftMax[i] = (maxStack.isEmpty()) ? i + 1 : i - maxStack.peek();
                minStack.push(i);
                maxStack.push(i);
            }
            minStack.clear();
            maxStack.clear();
            for (int i = arr.length - 1; i >= 0; i--) {
                while (!maxStack.isEmpty() && arr[maxStack.peek()] < arr[i]) maxStack.pop();
                while (!minStack.isEmpty() && arr[minStack.peek()] > arr[i]) minStack.pop();
                rightMin[i] = (minStack.isEmpty()) ? arr.length - i : minStack.peek() - i;
                rightMax[i] = (maxStack.isEmpty()) ? arr.length - i : maxStack.peek() - i;
                minStack.push(i);
                maxStack.push(i);
            }
            long totalContribution = 0;
            for (int i = 0; i < arr.length; i++) {
                long countMin = (long) leftMin[i] * rightMin[i]; // total subarrays where arr[i] will act as minimum
                long countMax = (long) leftMax[i] * rightMax[i]; // total subarrays where arr[i] will act as maximum
                totalContribution += (countMax - countMin) * arr[i]; // contribution of arr[i]
            }
            return (int) totalContribution;
        }
    }
}
