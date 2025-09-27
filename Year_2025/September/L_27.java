public class L_27 {
    public static void main(String[] args) {

    }

    private static class Solution {
        static {
            Solution solution = new Solution();
            for (int i = 0; i < 500; i++) {
                solution.largestTriangleArea(new int[][] { { 0, 0 } });
            }
        }

        public double largestTriangleArea(int[][] points) {
            int n = points.length;
            if (n == 1)
                return 0;
            double maxArea = 0;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 1; j < n - 1; j++) {
                    for (int k = j + 1; k < n; k++) {
                        double area = findAreaByShoelace(points[i], points[j], points[k]);
                        if (area > maxArea) {
                            maxArea = area;
                        }
                    }
                }
            }
            return maxArea;
        }

        private double findAreaByShoelace(int[] p1, int[] p2, int[] p3) {
            return 0.5 * Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1]));
        }

        private double findArea(int[] p1, int[] p2, int[] p3) {
            double d1 = findDistance(p1, p2), d2 = findDistance(p2, p3), d3 = findDistance(p3, p1);
            double s = (d1 + d2 + d3) / 2.0;
            return (double) Math.sqrt(s * (s - d1) * (s - d2) * (s - d3));
        }

        private double findDistance(int[] p1, int[] p2) {
            return (double) Math.sqrt((Math.pow(p1[0] - p2[0], 2)) + Math.pow(p1[1] - p2[1], 2));
        }
    }
}