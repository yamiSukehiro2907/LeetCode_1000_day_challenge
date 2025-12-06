package December.GFG;

import java.util.Arrays;

public class first_positive_missing_number {
    static void main() {
        int[] arr = {-57, 1, 2, 3, 89};
        System.out.println(find(arr));
    }

    private static int find(int[] arr) {
        int n = arr.length;
        for (int index = 0; index < n; index++) {
            if (arr[index] > 0 && arr[index] <= n) {
                int pos = arr[index] - 1;
                if (arr[index] != arr[pos]) {
                    swap(arr, index, pos);
                    index--;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        for (int index = 0; index < n; index++) {
            if (arr[index] != index + 1) return index + 1;
        }
        return n + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
