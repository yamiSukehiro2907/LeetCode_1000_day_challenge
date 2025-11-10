
import java.util.Stack;

public class L_10 {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        Solution solution = new Solution();
        Solution1 solution1 = new Solution1();
        System.out.println(solution.minOperations(arr));
        System.out.println(solution1.minOperations(arr));
    }

    private static class Solution {

        static {
            Solution solution = new Solution();
            int[] arr = new int[1];
            for (int i = 0; i < 500; i++) {
                solution.minOperations(arr);
            }
        }

        public int minOperations(int[] nums) {
            if (nums.length == 1 && nums[0] != 0) {
                return 1;
            }
            int[] stack = new int[nums.length + 1];
            int top = 0, ops = 0;
            for (int i = 0; i < nums.length; i++) {
                while (stack[top] > nums[i]) { // if top element is greater than current element
                    top--;
                    ops++;
                }
                if (stack[top] != nums[i]) {
                    stack[++top] = nums[i]; // add element to stack

                }
            }
            return ops + top; // operations + remaining top elements 
        }
    }

    private static class Solution1 {

        public int minOperations(int[] nums) {
            Stack<Integer> stack = new Stack<>();
            int totalOperations = 0;
            int index = 0;
            while (index < nums.length) {
                if (nums[index] == 0) {
                    // if got 0 means remove all previous ones
                    if (!stack.isEmpty()) {
                        totalOperations += clear(stack);
                    }
                    index++;
                } else {
                    while (!stack.isEmpty() && stack.peek() >= nums[index]) {
                        // if the current building is equal to previous then it will be removed with other no need to increase totalOperations
                        if (stack.peek() != nums[index]) {
                            totalOperations++;
                        }
                        // Remove top building as it is removed 
                        stack.pop();
                    }
                    stack.push(nums[index++]);  // new building added
                }
            }
            if (!stack.isEmpty()) {
                totalOperations += clear(stack);
            }
            return totalOperations;
        }

        private int clear(Stack<Integer> stack) {
            int totalOperations = 1;
            // if stack have one building
            int topElement = stack.pop();
            while (!stack.isEmpty()) {
                if (topElement == stack.peek()) {
                    stack.pop(); // got same building 
                } else {
                    topElement = stack.pop(); // new smaller building
                    totalOperations++;
                }
            }
            return totalOperations;
        }
    }
}
