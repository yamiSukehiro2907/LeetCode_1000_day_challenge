import java.util.*;

public class GFG_18 {
    public static void main(String[] args) {
        int[] arr = { 0 , 2 , 3 , 1 , 1 , 3 };
        Solution solution = new Solution();
        System.out.println(solution.nextGreater(arr));
    }

    private static class Solution {
        public ArrayList<Integer> nextGreater(int[] arr) {
            int lastMaxIndex = 0;
            int n = arr.length;
            int max = arr[0];
            for (int i = 0; i < n; i++) {
                if (arr[i] >= max) {
                    max = arr[i];
                    lastMaxIndex = i;
                }
            }
            int index = lastMaxIndex;
            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            Stack<Integer> stack = new Stack<>();
            while (index >= 0) {
                while (!stack.isEmpty() && arr[index] >= stack.peek()) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ans[index] = stack.peek();
                }
                stack.push(arr[index--]);
            }
            index = n - 1;
            while (index > lastMaxIndex) {
                while (!stack.isEmpty() && arr[index] >= stack.peek()) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ans[index] = stack.peek();
                }
                stack.push(arr[index--]);
            }
            ArrayList<Integer> list = new ArrayList<>();
            for (int num : ans) {
                list.add(num);
            }
            return list;
        }
    }
}
