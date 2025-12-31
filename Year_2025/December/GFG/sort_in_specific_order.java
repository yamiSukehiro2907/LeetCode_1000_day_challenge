package December.GFG;

import java.util.Arrays;

public class sort_in_specific_order {
    static void main() {
        int[] arr = {0, 4, 5, 3, 7, 2, 1, 10};
        Solution s = new Solution();
        s.sortIt(arr);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public void sortIt(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] % 2 == 1) {
                    arr[i] = arr[i] * -1;
                }
            }
            Arrays.sort(arr);
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 0) {
                    arr[i] = arr[i] * -1;
                }
            }
        }
    }

}
