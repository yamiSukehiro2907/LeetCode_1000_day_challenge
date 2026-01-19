package January;

import java.util.Stack;

public class remove_k_digits {
    static void main() {

    }

    static class Solution {
        public String removeKdig(String s, int k) {
            Stack<Character> stack = new Stack<>();
            for (char ch : s.toCharArray()) {
                while (!stack.isEmpty() && stack.peek() > ch && k > 0) {
                    k--;
                    stack.pop();
                }
                stack.push(ch);
            }
            while (k > 0 && !stack.isEmpty()) {
                stack.pop();
                k--;
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) sb.append(stack.pop());
            while (!sb.isEmpty() && sb.charAt(sb.length() - 1) == '0') sb.deleteCharAt(sb.length() - 1);
            if (sb.isEmpty()) return "0";
            return sb.reverse().toString();
        }
    }
}
