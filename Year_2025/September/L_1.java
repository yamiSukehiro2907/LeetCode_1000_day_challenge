import java.util.*;

public class L_1 {
    public static void main(String[] args) {
        int[][] classes = {{1, 2}, {3, 5}, {2, 2}};
        int extraStudents = 2;
        System.out.println(Solution.maxAverageRatio(classes, extraStudents));
    }

    static class Solution {
        public static double maxAverageRatio(int[][] classes, int extraStudents) {
            PriorityQueue<Double[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[0] > b[0]) return -1;
                else if (a[0] < b[0]) return 1;
                return 0;
            });
            for (int i = 0; i < classes.length; i++) {
                pq.add(new Double[]{(classes[i][0] + 1.0) / (classes[i][1] + 1.0) - (double)classes[i][0] / classes[i][1], (double) classes[i][0], (double) classes[i][1]});
            }
            while (extraStudents > 0) {
                Double[] temp = pq.poll();
                temp[1] = temp[1] + 1;
                temp[2] = temp[2] + 1;
                temp[0] = (temp[1] + 1.0) / (temp[1] + 1.0) - temp[1] / temp[2];
                pq.add(temp);
                extraStudents--;
            }
            double percent = 0.0;
            while (!pq.isEmpty()) {
                Double[] temp = pq.poll();
                percent += temp[1] / temp[2];
            }
            return percent / classes.length;
        }
    }
}