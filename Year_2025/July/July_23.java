public class July_23 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int maximumGain(String s, int x, int y) {
            int point = 0;
            char a = 'a', b = 'b';
            if (x < y) {
                int temp = y;
                y = x;
                x = temp;
                a = 'b';
                b = 'a';
            }
            int countA = 0, countB = 0;
            for (char ch : s.toCharArray()) {
                if (ch == a) {
                    countA++;
                } else if (ch == b) {
                    if (countA > 0) {
                        point += x;
                        countA--;
                    } else {
                        countB++;
                    }
                } else {
                    point += Math.min(countA, countB) * y;
                    countA = countB = 0;
                }
            }
            point += Math.min(countA, countB) * y;
            return point;
        }
    }
}