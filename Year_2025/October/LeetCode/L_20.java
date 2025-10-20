public class L_20 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] op = new String[] { "X++" };
        System.out.println(solution.finalValueAfterOperations(op));
    }

    private static class Solution {
        static {
            System.gc();
            Solution solution = new Solution();
            String[] op = new String[] { "X++" };
            for (int i = 0; i < 500; i++) solution.finalValueAfterOperations(op);
        }

        public int finalValueAfterOperations(String[] op) {
            int x = 0;
            for (String str : op) {
                if (str.contains("+")) x++;
                else x--;
            }
            return x;
        }
    }
    /*

    class Solution {
        public int finalValueAfterOperations(String[] op) {
            int x = 0;
            for (String str : op)
                x = (str.contains("+")) ? x + 1 : x - 1;
            return x;
        }
    }

    class Solution {
        public int finalValueAfterOperations(String[] operations) {
            int ans = 0;
            String str = "";
            for (int i = 0; i < operations.length; i++) {
                str = operations[i];
                if (str.charAt(0) == 'X') {
                    if (str.charAt(1) == '-')
                        ans--;
                    else
                        ans++;
                } else if (str.charAt(0) == '-')
                    ans--;
                else
                    ans++;
            }
            return ans;
        }
    }
    */
}