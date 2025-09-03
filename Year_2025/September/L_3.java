import java.util.Arrays;

public class L_3 {
    public static void main(String[] args) {
        int[][] points = { { 1, 3 }, { 1, 1 }, { 3, 1 } };
        Solution solution = new Solution();
        System.out.println(solution.numberOfPairs(points));
    }

    static class Solution {
        public int numberOfPairs(int[][] points) {
            Arrays.sort(points, (a, b) -> (a[0] == b[0]) ? b[0] - a[0] : a[0] - b[0]);
            int count = 0;
            for (int i = 0; i < points.length; i++) {
                long top = points[i][1];
                long min = Long.MIN_VALUE;
                for (int j = i + 1; j < points.length; j++) {
                    long y = points[j][1];
                    if (y <= top && y > min) {
                        count++;
                        min = y;
                        if (top == y) {
                            top--;
                        }
                    }
                }
            }
            return count;
        }
    }
}
