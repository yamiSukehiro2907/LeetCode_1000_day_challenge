package Year_2025.June.June_6;

public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public String robotWithString(String s) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            int minChar = 0;
            int[] stk = new int[s.length()];
            int top = -1;
            StringBuilder result = new StringBuilder();
            for (char c : s.toCharArray()) {
                while (count[minChar] == 0) {
                    minChar++;
                }
                while (top != -1 && stk[top] <= minChar) {
                    result.append((char) (stk[top--] + 'a'));
                }
                count[c - 'a']--;
                stk[++top] = c - 'a';
            }
            while (top != -1) {
                result.append((char) (stk[top--] + 'a'));
            }
            return result.toString();
        }
    }
/*
class Solution {
    public String robotWithString(String str) {
        StringBuilder stack = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        int[] freq = new int[26];
        int n = str.length();
        for (int i = 0; i < n; i++) {
            freq[str.charAt(i) - 'a']++;
        }
        int index = 0;
        while (index < n) {
            stack.append(str.charAt(index));
            freq[str.charAt(index) - 'a']--;
            while (stack.length() > 0 && stack.charAt(stack.length() - 1) <= find(freq)) {
                ans.append(stack.charAt(stack.length() - 1));
                stack = stack.deleteCharAt(stack.length() - 1);
            }
            index++;
        }
        ans.append(stack.reverse());
        return ans.toString();
    }

    private char find(int[] freq) {
        int smallest = 25;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0 && i < smallest) {
                smallest = i;
            }
        }
        return (char) (smallest + 'a');
    }
}
*/
}
