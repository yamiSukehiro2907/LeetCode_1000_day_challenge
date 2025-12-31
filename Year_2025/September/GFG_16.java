import java.util.Stack;

public class GFG_16 {
    public static void main(String[] args) {
        String[] arr = { "2", "3", "1", "*", "+", "9", "-" };
        GFG_16.Solution solution = new Solution();
        System.out.println(solution.evaluatePostfix(arr));
    }

    private static class Solution {
        public int evaluatePostfix(String[] arr) {
            Stack<Integer> stack = new Stack<>();
            for (String str : arr) {
                if (isOperator(str)) {
                    int num2 = stack.pop();
                    int num1 = stack.pop();
                    stack.push(operate(num1, num2, str));
                } else
                    stack.push(Integer.valueOf(str));
            }
            return stack.pop();
        }

        private boolean isOperator(String ch) {
            return ch.equals("*") || ch.equals("+") || ch.equals("-") || ch.equals("^") || ch.equals("/");
        }

        private int operate(int num1, int num2, String operand) {
            if ("+".equals(operand))
                return num1 + num2;
            if ("-".equals(operand))
                return num1 - num2;
            if ("*".equals(operand))
                return num1 * num2;
            if ("/".equals(operand))
                return (int) Math.floor(((double) num1 / (double) num2));
            return (int) (Math.pow(num1, num2));
        }
    }
}
