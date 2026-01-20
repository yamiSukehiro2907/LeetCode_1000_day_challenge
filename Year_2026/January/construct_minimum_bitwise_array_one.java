package January;

import java.util.Arrays;
import java.util.List;

public class construct_minimum_bitwise_array_one {
    static void main() {

    }

    static class Solution {
        private static int[] temp;

        static {
            temp = new int[1001];
            Arrays.fill(temp, -1);
            // Approach : Adding 1 to any number will flip the continuous 1s bit from last
            for (int original = 2; original < 1001; original++) {
                int num = original;
                if ((num & (num + 1)) != 0) {
                    int ans = Integer.MAX_VALUE;
                    for (int j = 0; j < 32; j++) {
                        int bit = (num >> j) & 1;
                        int possibleAns = (bit == 0) ? num : (num ^ (1 << j));
                        // if at j position any bit is set unset and check if answer can be made
                        if (((possibleAns | (possibleAns + 1)) == num) && (possibleAns < ans)) ans = possibleAns;
                    }
                    if (ans != Integer.MAX_VALUE) temp[original] = ans;
                } else {
                    // if it has only 1s then unset the last bit and that will be answer
                    int lastPosition = -1;
                    while (num > 0) {
                        lastPosition++;
                        num = num >> 1;
                    }
                    temp[original] = original ^ (1 << lastPosition);
                }
            }
        }

        public int[] minBitwiseArray(List<Integer> nums) {
            int[] ans = new int[nums.size()];
            for (int i = 0; i < nums.size(); i++) ans[i] = temp[nums.get(i)];
            return ans;
        }
    }
}
