package December.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class count_unique_trapezoids2 {

    static void main() {
        Solution solution = new Solution();
        int[][] points = {{71, -89}, {-75, -89}, {-9, 11}, {-24, -89}, {-51, -89}, {-77, -89}, {42, 11}};
        System.out.println(solution.countTrapezoids(points));
    }


    private static class Solution {
        public int countTrapezoids(int[][] points) {
            Map<String, Map<String, List<Pair>>> map = new HashMap<>();
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    String slope = findSlope(points[i], points[j]);
                    String intercept = findIntercept(points[i], points[j], slope);
                    map.putIfAbsent(slope, new HashMap<>());
                    map.get(slope).putIfAbsent(intercept, new ArrayList<>());
                    map.get(slope).get(intercept).add(new Pair(points[i], points[j]));
                }
            }
            int totalCount = 0;
            for (String key : map.keySet()) {
                Map<String, List<Pair>> temp = map.get(key);
                if (temp.size() < 2) continue;
                List<List<Pair>> list = new ArrayList<>(temp.values());
                for (int i = 0; i < list.size() - 1; i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        for (Pair p1 : list.get(i)) {
                            for (Pair p2 : list.get(j)) {
                                print(p1, p2);
                                if (isValid(p1, p2)) {
                                    totalCount++;
                                    System.out.println("Added");
                                }
                            }
                        }
                    }
                }
            }
            return totalCount;
        }

        private boolean isValid(Pair p1, Pair p2) {
            int[] A = p1.point1;
            int[] B = p1.point2;
            int[] C = p2.point1;
            int[] D = p2.point2;
            if (samePoint(A, C) || samePoint(A, D) || samePoint(B, C) || samePoint(B, D)) {
                return false;
            }
            if (isValidConvexQuad(A, B, C, D)) return true;
            return isValidConvexQuad(A, B, D, C);
        }

        private boolean isValidConvexQuad(int[] A, int[] B, int[] C, int[] D) {
            if (!isConvexQuad(A, B, C, D)) return false;

            if (segmentsIntersect(A, B, C, D)) return false;
            return !segmentsIntersect(B, C, D, A);
        }

        private boolean segmentsIntersect(int[] p1, int[] p2, int[] p3, int[] p4) {
            int d1 = direction(p3, p4, p1);
            int d2 = direction(p3, p4, p2);
            int d3 = direction(p1, p2, p3);
            int d4 = direction(p1, p2, p4);

            return ((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
                    ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0));
        }

        private int direction(int[] p1, int[] p2, int[] p3) {
            return crossProduct(p1, p2, p3);
        }

        private boolean samePoint(int[] p1, int[] p2) {
            return p1[0] == p2[0] && p1[1] == p2[1];
        }

        private boolean isConvexQuad(int[] A, int[] B, int[] C, int[] D) {
            int cross1 = crossProduct(A, B, C);
            int cross2 = crossProduct(B, C, D);
            int cross3 = crossProduct(C, D, A);
            int cross4 = crossProduct(D, A, B);
            boolean allPositive = cross1 > 0 && cross2 > 0 && cross3 > 0 && cross4 > 0;
            boolean allNegative = cross1 < 0 && cross2 < 0 && cross3 < 0 && cross4 < 0;

            return allPositive || allNegative;
        }

        private int crossProduct(int[] p1, int[] p2, int[] p3) {
            long dx1 = p2[0] - p1[0];
            long dy1 = p2[1] - p1[1];
            long dx2 = p3[0] - p2[0];
            long dy2 = p3[1] - p2[1];

            long result = dx1 * dy2 - dy1 * dx2;

            if (result > 0) return 1;
            if (result < 0) return -1;
            return 0;
        }

        private String findSlope(int[] point1, int[] point2) {
            int dx = point2[0] - point1[0];
            int dy = point2[1] - point1[1];
            if (dx == 0) return "INF";
            int gcd = gcd(Math.abs(dx), Math.abs(dy));
            dx /= gcd;
            dy /= gcd;
            if (dx < 0) {
                dx = -dx;
                dy = -dy;
            }
            return dy + "/" + dx;
        }

        private String findIntercept(int[] point1, int[] point2, String slope) {
            if (slope.equals("INF")) return "x=" + point1[0];
            int dx = point2[0] - point1[0];
            int dy = point2[1] - point1[1];
            int numerator = point1[1] * dx - dy * point1[0];
            int gcd = gcd(Math.abs(numerator), Math.abs(dx));
            numerator /= gcd;
            int denominator = dx / gcd;
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            return numerator + "/" + denominator;
        }

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        private static class Pair {
            int[] point1;
            int[] point2;

            Pair(int[] point1, int[] point2) {
                this.point1 = point1;
                this.point2 = point2;
            }
        }

        static void print(Pair p1, Pair p2) {
            System.out.print("Points: ");
            System.out.print(p1.point1[0] + " " + p1.point1[1] + " ");
            System.out.print(p1.point2[0] + " " + p1.point2[1] + " ");
            System.out.print(p2.point1[0] + " " + p2.point1[1] + " ");
            System.out.print(p2.point2[0] + " " + p2.point2[1] + " ");
            System.out.println();
        }
    }
}