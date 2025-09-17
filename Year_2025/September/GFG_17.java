import java.util.Stack;

public class GFG_17 {
    public static void main(String[] args) {
        String s = "10[a3[b]]";
        System.out.println(Solution.decodeString(s));
    }

    private static class Solution {
        static String decodeString(String s) {
            Stack<Character> stack = new Stack<>();
            for (char ch : s.toCharArray()) {
                if (ch == ']') {
                    StringBuilder sb = new StringBuilder();
                    while (!stack.isEmpty() && stack.peek() != '[') {
                        sb.append(stack.pop());
                    }
                    sb.reverse();
                    stack.pop();
                    StringBuilder Num = new StringBuilder();
                    while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                        Num.append(stack.pop());
                    }
                    Num.reverse();
                    int num = Integer.parseInt(Num.toString());
                    System.out.println(num);
                    for (int i = 0; i < num; i++) {
                        for (char c : sb.toString().toCharArray()) {
                            stack.push(c);
                        }
                    }
                } else {
                    stack.push(ch);
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
            return sb.toString();
        }
    }
}
