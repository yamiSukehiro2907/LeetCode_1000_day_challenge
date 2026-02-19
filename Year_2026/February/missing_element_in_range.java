package February;

import java.util.ArrayList;

public class missing_element_in_range {
    static void main() {

    }

    static class Solution {
        public ArrayList<Integer> missingRange(int[] arr, int low, int high) {
            int[] freq = new int[high - low + 1];
            for (int num : arr) if (num >= low && num <= high) freq[num - low]++;
            ArrayList<Integer> missing = new ArrayList<>();
            for (int num = low; num <= high; num++) if (freq[num - low] == 0) missing.add(num);
            return missing;
        }
    }
}
