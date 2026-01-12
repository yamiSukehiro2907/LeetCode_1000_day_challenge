package January;

public class minimum_time_visiting_all_points {
    static void main() {

    }

    static class Solution {
        public int minTimeToVisitAllPoints(int[][] points) {
            int totalDistance = 0;
            for (int i = 1; i < points.length; i++) totalDistance += move(points[i - 1], points[i]);
            return totalDistance;
        }

        private static int move(int[] from, int[] to) {
            return Math.max(Math.abs(from[0] - to[0]), Math.abs(from[1] - to[1]));
        }
    }
}
