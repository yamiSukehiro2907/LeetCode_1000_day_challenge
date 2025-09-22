import java.util.*;

public class GFG_22 {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 50, 10, 70, 30 };
        Solution solution = new Solution();
        System.out.println(solution.maxOfMins(arr));
    }

    // O(n)
    private static class Solution {
        public ArrayList<Integer> maxOfMins(int[] arr) {
            int n = arr.length;
            int[] temp = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                    int element = arr[stack.pop()];
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    int rightIndex = i;
                    int windowSize = rightIndex - leftIndex - 1;
                    temp[windowSize - 1] = Math.max(element, temp[windowSize - 1]);
                }
                stack.add(i);
            }
            while (!stack.isEmpty()) {
                int element = arr[stack.pop()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = n;
                int windowSize = rightIndex - leftIndex - 1;
                temp[windowSize - 1] = Math.max(element, temp[windowSize - 1]);
            }
            for(int i = n- 2 ; i >= 0 ; i--){
                temp[i] = Math.max(temp[i] , temp[i + 1]);
            }
            ArrayList<Integer> ans = new ArrayList<>();
            for(int num : temp) ans.add(num);
            return ans;
        }
    }

    // O(n^2)
    private static class Solution2 {
        public ArrayList<Integer> maxOfMins(int[] arr) {
            int n = arr.length;
            ArrayList<Integer> ans = new ArrayList<>();
            for (int k = 1; k <= n; k++) {
                Deque<Integer> deque = new ArrayDeque<>();
                for (int i = 0; i < k; i++) {
                    while (!deque.isEmpty() && arr[i] <= arr[deque.peekLast()])
                        deque.pollLast();
                    deque.add(i);
                }
                int maxMin = Integer.MIN_VALUE;
                for (int i = k; i < n; i++) {
                    maxMin = Math.max(maxMin, arr[deque.peekFirst()]);
                    while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                        deque.pollFirst();
                    while (!deque.isEmpty() && arr[i] <= arr[deque.peekLast()])
                        deque.pollLast();
                    deque.add(i);
                }
                maxMin = Math.max(maxMin, arr[deque.peekFirst()]);
                ans.add(maxMin);
            }
            return ans;
        }
    }
}