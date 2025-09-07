import java.util.Arrays;

public class L_7 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.sumZero(6)));
    }

    static class Solution {
        public int[] sumZero(int n) {
            int[] ans = new int[n];
            int leftIndex = 0, rightIndex = ((n & 1) != 0) ? (n / 2) + 1 : n / 2;
            int num = 1;
            while (leftIndex < (n / 2)) {
                ans[leftIndex++] = -1 * num;
                ans[rightIndex++] = num;
                num++;
            }
            return ans;
        }
    }
}
