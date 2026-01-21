package January;

import java.util.ArrayList;
import java.util.Stack;

public class stock_span_problem {
    static void main() {

    }

    static class Solution {
        public ArrayList<Integer> calculateSpan(int[] arr) {
            Stack<int[]> stack = new Stack<>();
            ArrayList<Integer> ans = new ArrayList<>();
            for (int num : arr) {
                int[] temp = new int[]{num, 1};
                while (!stack.isEmpty()) {
                    int[] prev = stack.peek();
                    if (prev[0] > num) break;
                    else {
                        temp[1] += prev[1];
                        stack.pop();
                    }
                }
                ans.add(temp[1]);
                stack.push(temp);
            }
            return ans;
        }
    }
}
