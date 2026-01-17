package January;

public class max_square_area_in_intersecting_rectangles {
    static void main() {
        Solution sol = new Solution();
        int[][] bottomLeft = {{1, 3}, {4, 3}};
        int[][] topRight = {{5, 4}, {5, 5}};
        System.out.println(sol.largestSquareArea(bottomLeft, topRight));
    }

    static class Solution {
        public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
            long maxIntersectingArea = 0L;
            for (int i = 0; i < bottomLeft.length; i++) {
                for (int j = 0; j < i; j++) {
                    long commonArea = findCommonArea(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j]);
                    if (commonArea > maxIntersectingArea) maxIntersectingArea = commonArea;
                }
            }
            return maxIntersectingArea;
        }

        private long findCommonArea(int[] bottomLeft1, int[] topRight1, int[] bottomLeft2, int[] topRight2) {
            int x1 = bottomLeft1[0], y1 = bottomLeft1[1], x2 = topRight1[0], y2 = topRight1[1];
            int x3 = bottomLeft2[0], y3 = bottomLeft2[1], x4 = topRight2[0], y4 = topRight2[1];
            long width = getSide(x1, x2, x3, x4), length = getSide(y1, y2, y3, y4);
            long side = Math.min(width, length);
            return side * side;
        }

        private long getSide(int cord1, int cord2, int cord3, int cord4) {
            System.out.println(cord1 + " " + cord2 + " " + cord3 + " " + cord4);
            if (cord3 >= cord1 && cord4 <= cord2) return cord4 - cord3;
            else if (cord1 >= cord3 && cord2 <= cord4) return cord2 - cord1;
            else if (cord4 <= cord2 && cord1 <= cord4) return cord4 - cord1;
            else if (cord2 <= cord4 && cord3 <= cord2) return cord2 - cord3;
            return 0;
        }

    }
}
