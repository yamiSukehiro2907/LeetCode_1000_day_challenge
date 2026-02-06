package February;

import java.util.Arrays;

public class happiest_triplet {
    static void main() {

    }

    static class Solution {
        int[] smallestDiff(int[] arr1, int[] arr2, int[] arr3) {
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            Arrays.sort(arr3);
            int[] ans = new int[]{Integer.MAX_VALUE, 0, 0};
            int i = 0, j = 0, k = 0;
            while (i < arr1.length && j < arr2.length && k < arr3.length) {
                int min = Math.min(arr1[i], Math.min(arr2[j], arr3[k]));
                int max = Math.max(arr1[i], Math.max(arr2[j], arr3[k]));
                int diff = max - min;
                int prevDiff = ans[0] - ans[2];
                int sum = arr1[i] + arr2[j] + arr3[k];
                int prevSum = ans[0] + ans[1] + ans[2];
                if (diff < prevDiff || (diff == prevDiff && prevSum > sum)) {
                    ans = new int[]{arr1[i], arr2[j], arr3[k]};
                    Arrays.sort(ans);
                    int temp = ans[0];
                    ans[0] = ans[2];
                    ans[2] = temp;
                }
                if (min == arr1[i]) i++;
                else if (min == arr2[j]) j++;
                else k++;
            }
            return ans;
        }
    }
}
