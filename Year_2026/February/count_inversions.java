package February;

import java.util.Arrays;

public class count_inversions {
    static void main() {
        int[] arr = {2, 4, 1, 3, 5};
        System.out.println(Solution.inversionCount(arr));
    }

    static class Solution {
        static int inversionCount(int[] arr) {
            return countInversion(arr, 0, arr.length - 1);
        }

        static int countInversion(int[] arr, int start, int end) {
            if (start > end) return 0;
            if (start == end) return 0;
            int count = 0;
            int mid = start + ((end - start) >> 1);
            count += countInversion(arr, start, mid);
            count += countInversion(arr, mid + 1, end);
            count += merge(arr, start, mid, end);
            return count;
        }

        static int merge(int[] arr, int start, int mid, int end) {
            int count = 0;
            int[] temp = new int[end - start + 1];
            int i = start, j = mid + 1, k = 0;
            while (i <= mid && j <= end) {
                if (arr[j] < arr[i]) temp[k++] = arr[j++];
                else {
                    count += (j - mid - 1);
                    temp[k++] = arr[i++];
                }
            }
            while (i <= mid) {
                count += (end - mid);
                temp[k++] = arr[i++];
            }
            while (j <= end) temp[k++] = arr[j++];
            k = 0;
            for (i = start; i <= end; i++) arr[i] = temp[k++];
            return count;
        }
    }
}


