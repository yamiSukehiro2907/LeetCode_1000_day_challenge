import java.util.Arrays;

public class L_2 {
    public static void main(String[] args) {
        Solution Solution = new Solution();
        int[][] points = { { 1, 1 }, { 1, 3 }, { 3, 1 } };
        System.out.println(Solution.numberOfPairs(points));
    }

    static class Solution {
        public int numberOfPairs(int[][] points) {
            Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            System.out.println("Sorted Points: ");
            for (int[] num : points) {
                System.out.print("(" + num[0] + " , " + num[1] + ") , ");
            }
            System.out.println();
            int cnt = 0;
            for (int i = 0; i < points.length; i++) {
                int y0 = points[i][1];
                System.out.println("Current Point: " + points[i][0] + " " + points[i][1]);
                int bot = Integer.MIN_VALUE, top = y0;
                for (int j = i + 1; j < points.length; j++) {
                    int y1 = points[j][1];
                    System.out.println("Point: " + points[j][0] + " " + points[j][1] + " , top: " + top + " , bot: " + bot);
                    if (y1 <= top && y1 > bot) {
                        System.out.println("Formed Pair...  Updated values");
                        cnt++;
                        bot = y1;
                        System.out.print("Bot: " + bot);
                        if (y1 == top) {
                            System.out.print(" top: " + top);
                            top--;
                        }
                        System.out.println();

                    }
                }
            }
            return cnt;
        }
    }

    /*
     * class Solution {
     * public int numberOfPairs(int[][] points) {
     * int count = 0;
     * for (int i = 0; i < points.length - 1; i++) {
     * for (int j = i + 1; j < points.length; j++) {
     * int x1 = points[i][0];
     * int y1 = points[i][1];
     * int x2 = points[j][0];
     * int y2 = points[j][1];
     * if ((x1 <= x2 && y1 >= y2) || (x2 <= x1 && y2 >= y1)) {
     * int xMin = Math.min(x1, x2);
     * int xMax = Math.max(x1, x2);
     * int yMin = Math.min(y1, y2);
     * int yMax = Math.max(y1, y2);
     * boolean possible = true;
     * for (int k = 0; k < points.length; k++) {
     * if (k != i && k != j) {
     * int x = points[k][0], y = points[k][1];
     * if (x >= xMin && x <= xMax && y <= yMax && y >= yMin) {
     * possible = false;
     * break;
     * }
     * }
     * }
     * if (possible) {
     * count++;
     * }
     * }
     * }
     * }
     * return count;
     * }
     * }
     */

}
