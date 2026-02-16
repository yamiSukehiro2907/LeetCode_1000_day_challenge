package February;

public class reverse_bits {
    static void main() {

    }

    static class Solution {
        public int reverseBits(int n) {
            int num = 0;
            for(int i = 0 ; i < 32 ; i++) {num |= ((n & 1) << (31 - i)); n >>= 1;}
            return num;
        }
    }
}
