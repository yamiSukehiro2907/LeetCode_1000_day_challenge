package January;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class next_element_with_greater_frequency {
    static void main() {

    }

    static class Solution {
        public ArrayList<Integer> nextFreqGreater(int[] arr) {
            int max = arr[0];
            for (int num : arr) if (num > max) max = num;
            int[] freq = new int[max + 1];
            for (int num : arr) freq[num]++;
            Stack<Integer> stack = new Stack<>();
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = arr.length - 1; i >= 0; i--) {
                while (!stack.isEmpty() && freq[stack.peek()] <= freq[arr[i]]) stack.pop();
                if (stack.isEmpty()) ans.add(-1);
                else ans.add(stack.peek());
                stack.push(arr[i]);
            }
            Collections.reverse(ans);
            return ans;
        }
    }
}
