package February;

import java.util.ArrayList;

public class union_of_arrays_with_duplicates {
    static void main() {

    }

    static class Solution {
        public static ArrayList<Integer> findUnion(int[] a, int[] b) {
            int max = a[0];
            for (int num : a) if (max < num) max = num;
            for (int num : b) if (max < num) max = num;
            int[] freq = new int[max + 1];
            for (int num : a) freq[num]++;
            for (int num : b) freq[num]++;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i <= max; i++) if (freq[i] > 0) list.add(i);
            return list;
        }
    }
}
