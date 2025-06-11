import java.util.*;

public class Approach {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxDifference("1122211", 3));
    }

    static class Solution {
        private Map<Integer, Integer> oddMap;
        private Map<Integer, Integer> evenMap;
        private int left, right;

        public int maxDifference(String s, int k) {
            this.oddMap = new HashMap<>();
            this.evenMap = new HashMap<>();
            this.left = 0;
            this.right = 0;
            for (; right < k; right++) {
                fill(s.charAt(right));
            }
            while (left + k < right) {
                if (shouldFill()) {
                    fill(s.charAt(++right));
                }
            }
            return 0;
        }

        private void fill(char ch) {
            boolean oddContains = oddMap.containsKey(ch - '0');
            boolean evenContains = evenMap.containsKey(ch - '0');
            if (!oddContains && !evenContains) {
                oddMap.put(ch - '0', 1);
            } else if (oddContains) {
                evenMap.put(ch - '0', oddMap.get(ch - '0') + 1);
                oddMap.remove(ch - '0');
            } else {
                oddMap.put(ch - '0', evenMap.get(ch - '0') + 1);
                evenMap.remove(ch - '0');
            }
        }

        private boolean shouldFill() {
            if (oddMap.isEmpty() || evenMap.isEmpty()) {
                return true;
            }
            return false;
        }
    }
}