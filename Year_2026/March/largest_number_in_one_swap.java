package March;

import java.util.Arrays;

public class largest_number_in_one_swap {
    static void main() {

    }

    static class Solution {
        public String largestSwap(String s) {
            int[] freq = new int[10];
            Arrays.fill(freq, -1);
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) freq[arr[i] - '0'] = i;
            for (int num = 9; num > 0; num--) {
                if (freq[num] == -1) continue;
                for (int i = 0; i < arr.length; i++) {
                    int digit = arr[i] - '0';
                    if (digit < num && freq[num] > i) {
                        char temp = arr[freq[num]];
                        arr[freq[num]] = arr[i];
                        arr[i] = temp;
                        return new String(arr);
                    }
                }
            }
            return s;
        }
    }
}
