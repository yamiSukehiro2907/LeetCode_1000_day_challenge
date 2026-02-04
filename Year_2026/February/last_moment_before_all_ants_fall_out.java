package February;

public class last_moment_before_all_ants_fall_out {
    static void main() {

    }

    static class Solution {
        public int getLastMoment(int n, int left[], int right[]) {
            int ans = 0;
            for (int num : left) ans = Math.max(num, ans);
            for (int num : right) ans = Math.max(n - num, ans);
            return ans;
        }
    }
}
