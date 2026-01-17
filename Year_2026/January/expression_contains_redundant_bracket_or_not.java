package January;

import java.util.Stack;

public class expression_contains_redundant_bracket_or_not {
    static void main() {
        String s = "(a+(b+(c+d)))";
        System.out.println(Solution.checkRedundancy(s));
    }

    static class Solution {
        public static boolean checkRedundancy(String s) {
            char[] arr = s.toCharArray();
            int index = 0;
            Stack<Character> stack = new Stack<>();
            for (char ch : arr) {
                index++;
                printStack(stack);
                System.out.println("Char: " + ch + " Index: " + index);
                if (ch == '(' || isOperand(ch)) stack.push(ch);
                else if (ch == ')') {
                    if (stack.isEmpty()) return true;
                    if (stack.peek() == '(') return true;
                    while (!stack.isEmpty() && stack.peek() != '(') stack.pop();
                    stack.pop();
                }
            }
            while (!stack.isEmpty()) if (!isOperand(stack.pop())) return true;
            return false;
        }

        private static boolean isOperand(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/';
        }
    }

    static void printStack(Stack<Character> stack) {
        Stack<Character> stack2 = new Stack<>();
        while (!stack.isEmpty()) stack2.push(stack.pop());
        while (!stack2.isEmpty()) {
            char ch = stack2.pop();
            System.out.print(ch + " -> ");
            stack.push(ch);
        }
    }

}
