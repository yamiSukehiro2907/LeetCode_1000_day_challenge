package February;

public class binary_gap {
    static void main() {

    }

    static class Solution {
        public int binaryGap(int n) {
            int start = 32, maxLength = 0;
            for (int i = 31; i >= 0; i--) {
                if (((n >> i) & 1) == 1) {
                    if (start != 32) {
                        int length = start - i;
                        if (length > maxLength) maxLength = length;
                    }
                    start = i;
                }
            }
            return maxLength;
        }
    }
}
