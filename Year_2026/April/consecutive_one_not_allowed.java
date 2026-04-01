package April;

import java.util.Arrays;

public class consecutive_one_not_allowed {
    static void main() {
        int n = 3;
        Solution sol = new Solution();
        System.out.println(sol.countStrings(n));
    }

    static class Solution {
        int countStrings(int n) {
            int[] zerodp = new int[n];
            int[] onedp = new int[n];
            zerodp[0] = 1;
            onedp[0] = 1;
            for (int i = 2; i <= n; i++) {
                zerodp[i - 1] = zerodp[i - 2] + onedp[i - 2];
                onedp[i - 1] = zerodp[i - 2];
            }
            System.out.println(Arrays.toString(zerodp));
            System.out.println(Arrays.toString(onedp));
            return zerodp[n - 1] + onedp[n - 1];
        }
    }
}
