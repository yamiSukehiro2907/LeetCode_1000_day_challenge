public class and_in_range {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.andInRange(8, 13));
    }

    /// [...](https://www.geeksforgeeks.org/problems/count-total-set-bits-1587115620/1)

    private static class Solution {
        public int andInRange(int l, int r) {
            int ans = 0;
            for (int i = 0; i < 32; i++) if (setBit(l, r, i)) ans |= (1 << i);
            /// for each bit check whether it will be set bit or not
            return ans;
        }

        private boolean setBit(int l, int r, int pos) {
            int leftBit = (l >> pos) & 1;
            int rightBit = (r >> pos) & 1;
            if (leftBit == 1 && rightBit == 1) return (r - l) < (1 << pos);
            /// if and only if they are 1 and the gap between them is less than maximum gap b/w flip of 0s and 1s
            return false;
        }
    }

}
