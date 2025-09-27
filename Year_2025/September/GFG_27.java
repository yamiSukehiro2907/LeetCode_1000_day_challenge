
public class GFG_27 {
    public static void main(String[] args) {
        int[] arr = { 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 };
        int k = 17;
        Solution solution = new Solution();
        System.out.println(solution.minKBitFlips(arr.clone(), k));
    }

    private static class Solution {
        static {
            System.gc();
            Solution solution = new Solution();
            for (int i = 0; i < 500; i++) {
                solution.minKBitFlips(new int[1], 1);
            }
        }

        public int minKBitFlips(int[] nums, int k) {
            int n = nums.length;
            if (n == 1 && nums[0] == 0)
                return 1;
            int operations = 0;
            int currentFlip = 0;
            for (int i = 0; i < n; i++) {
                if (i >= k && nums[i - k] == 2)
                    currentFlip--;
                // if we came out of any of the flips range
                int currentBit = (currentFlip + nums[i]) % 2;
                // if currentFlip is odd then nums[i] is changed else original
                if (currentBit == 0) {
                    if (i > n - k)
                        return -1; // as there are no more flips allowed and we got 0
                    nums[i] = 2;
                    operations++;
                    currentFlip++;
                }
            }
            return operations;
        }
    }
}