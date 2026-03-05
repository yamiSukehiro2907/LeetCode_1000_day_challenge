package March;

public class minimum_changes_to_make_alternating_string {
    static void main() {

    }

    static class Solution {
        public int minOperations(String s) {
            char[] arr = s.toCharArray();
            return Math.min(change(arr, true), change(arr, false));
        }

        private static int change(char[] arr, boolean one) {
            int ops = 0;
            for (char ch : arr) {
                if (ch == '1' && !one) ops++;
                if (ch == '0' && one) ops++;
                one = !one;
            }
            return ops;
        }
    }
}
