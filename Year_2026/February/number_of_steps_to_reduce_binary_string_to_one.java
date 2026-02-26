package February;

public class number_of_steps_to_reduce_binary_string_to_one {
    static void main() {

    }

    static class Solution {
        public int numSteps(String s) {
            int n = s.length(), carry = 0, steps = 0;
            for (int i = n - 1; i > 0; i--) {
                if (s.charAt(i) - '0' + carry == 1) {
                    /// we have 1 here so converting to even will cost 1
                    steps++;
                    carry = 1;
                    /// we will get carry 1
                }
                steps++;
                /// we removed last zero
            }
            return carry + steps;
        }
    }
}
