import java.util.Arrays;

public class GFG_10 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public String largestSwap(String s) {
            int[] freq = new int[10];
            char[] arr = s.toCharArray();
            Arrays.fill(freq, -1);
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - '0'] = i;
            }
            for (int i = 9; i > 0; i--) {
                if (freq[i] == -1)
                    continue;
                for (int j = 0; j < arr.length; j++) {
                    int digit = arr[j] - '0';
                    if (digit < i && freq[i] > j) {
                        char temp = arr[freq[i]];
                        arr[freq[i]] = arr[j];
                        arr[j] = temp;
                        return new String(arr, 0, arr.length);
                    }
                }
            }
            return s;
        }
    }
}
