public class L1616 {
    public static void main(String[] args) {
        String a = "cddbcdbdc";
        String b = "cdbccbddc";
        Solution solution = new Solution();
        System.out.println(solution.checkPalindromeFormation(a, b));
    }

    private static class Solution {
        public boolean checkPalindromeFormation(String a, String b) {
            if (a.length() == 1)
                return true;
            if (check(a, b))
                return true;
            return check(b, a);
        }

        private boolean check(String a, String b) {
            int length = a.length();
            int prefix = (length % 2 == 0) ? (length / 2) - 1 : (length / 2) - 1;
            int suffix = (length % 2 == 0) ? (length / 2) : (length / 2) + 1;
            while (prefix >= 0 && suffix < length) {
                if (a.charAt(prefix) == a.charAt(suffix)) {
                    prefix--;
                    suffix++;
                } else
                    break;
            }
            if (checkParts(prefix, a, suffix, b))
                return true;
            return checkParts(prefix, b, suffix, a);
        }

        private boolean checkParts(int prefix, String a, int suffix, String b) {
            int length = a.length();
            while (prefix >= 0 && suffix < length) {
                if (a.charAt(prefix) == b.charAt(suffix)) {
                    prefix--;
                    suffix++;
                } else
                    return false;
            }
            return true;
        }
    }
}
