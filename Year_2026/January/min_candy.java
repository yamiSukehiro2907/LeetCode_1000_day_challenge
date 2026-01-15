package January;

import java.util.Arrays;

public class min_candy {

    static void main() {
        int[] arr = {4, 3, 2, 0};
        Solution sol = new Solution();
        System.out.println(sol.minCandy(arr));
    }

    static class Solution {
        public int minCandy(int[] arr) {
            if (arr.length == 1) return 1;
            int[] left = new int[arr.length];
            int[] right = new int[arr.length];
            Arrays.fill(left, 1);
            Arrays.fill(right, 1);
            for (int i = 1; i < arr.length; i++) if (arr[i] > arr[i - 1]) left[i] += left[i - 1];
            for (int i = arr.length - 2; i >= 0; i--) if (arr[i] > arr[i + 1]) right[i] += right[i + 1];
            int totalCandy = 0;
            for(int i = 0; i < arr.length; i++) totalCandy += Math.max(left[i], right[i]);
            return totalCandy;
        }
    }

}
