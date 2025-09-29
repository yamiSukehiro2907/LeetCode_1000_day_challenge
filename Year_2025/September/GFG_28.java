import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class GFG_28 {
    public static void main(String[] args) {
        int[] arr = { 1, 1, -8, 8, 10, -2, 4, 7, -7, 8, 3, 6, -9 };
        int a = 2;
        int b = 6;
        Solution solution = new Solution();
        System.out.println(solution.maxSubarrSum(arr, a, b));
    }

    private static class Solution {
        public int maxSubarrSum(int[] arr, int a, int b) {
            int n = arr.length;
            int maxSum = Integer.MIN_VALUE;
            Deque<Integer> deque = new ArrayDeque<>();
            int[] prefix = new int[n + 1];
            for (int i = 0; i < n; i++)
                prefix[i + 1] = prefix[i] + arr[i];
            System.out.print("Prefix: ");
            System.out.println(Arrays.toString(prefix));
            for (int i = a; i <= n; i++) {
                int validStart = i - a;
                if (validStart >= 0) {
                    System.out.println("Before checking vaildation: ");
                    print(deque);
                    while (!deque.isEmpty() && prefix[deque.peekLast()] >= prefix[validStart]) {
                        deque.pollLast();
                    }
                    deque.offerLast(validStart);
                }
                System.out.println("Removing out of range ones: ");
                print(deque);
                while (!deque.isEmpty() && deque.peekFirst() < i - b) {
                    deque.pollFirst();
                }
                System.out.println("Removed: ");
                print(deque);
                if (!deque.isEmpty()) {
                    maxSum = Math.max(maxSum, prefix[i] - prefix[deque.peekFirst()]);
                }
                System.out.println("MaxSum: " + maxSum);
            }
            return maxSum;
        }
    }

    private static void print(Deque<Integer> queue) {
        int size = queue.size();
        System.out.print("Deque:- ");
        for (int i = 0; i < size; i++) {
            int num = queue.pollFirst();
            System.out.print(" " + num + " ");
            if (i != size - 1) {
                System.out.print(",");
            }
            queue.addLast(num);
        }
        System.out.println();
    }
}
