package March;

public class complement_of_10_base_integer {
    static void main() {

    }

    static class Solution {
        public int bitwiseComplement(int n) {
            int ans = 0, index = 31;
            while (index > 0 && ((n >> index) & 1) == 0) index--;
            while (index >= 0) {
                if (((n >> index) & 1) == 0) ans |= (1 << index);
                index--;
            }
            return ans;
        }
    }
}
