package April;

public class robot_return_to_origin {
    static void main() {

    }

    static class Solution {
        public boolean judgeCircle(String moves) {
            int[] freq = new int[26];
            for (char ch : moves.toCharArray()) freq[ch - 'A']++;
            return freq['L' - 'A'] == freq['R' - 'A'] && freq['U' - 'A'] == freq['D' - 'A'];
        }
    }
}
