import java.util.Stack;

public class GFG_20 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public static int longestSubarray(int[] arr) {
            int n = arr.length;
            int maxLen = 0;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= n; i++) {
                int nge = (i == n ? Integer.MAX_VALUE : arr[i]);
                while (!stack.isEmpty() && arr[stack.peek()] < nge) {
                    int curr = arr[stack.pop()];
                    int len = stack.isEmpty() ? i : i - stack.peek() - 1;
                    if (len >= curr) maxLen = Math.max(maxLen, len);
                }
                stack.push(i);
            }
            return maxLen;
        }
    }
}