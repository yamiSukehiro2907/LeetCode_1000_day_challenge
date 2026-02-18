package February;

public class number_with_alternating_bits {
    static void main() {

    }

    static class Solution {
        public boolean hasAlternatingBits(int n) {
            int index = 31;
            while (index >= 0 && (((n >> index) & 1) == 0)) index--;
            while (index > 0) {
                if (((n >> index) & 1) == ((n >> (index - 1)) & 1)) return false;
                index--;
            }
            return true;
        }
    }
}
