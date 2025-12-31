package December.GFG;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class travelling_salesman_problem {
    static void main() {
        int[][] cost = {{0, 1000, 5000},
                {5000, 0, 1000},
                {1000, 5000, 0}};
        Solution sol = new Solution();
        System.out.println(sol.tsp(cost));
    }

    private static class Solution {
        public int tsp(int[][] cost) {
            int n = cost.length;
            if (n == 1) return 0;
            int fullMask = (1 << n) - 1;
            int[][] distance = new int[1 << n][n];
            for (int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            distance[1][0] = 0;
            queue.add(new int[]{0, 0, 1});
            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int u = temp[0];
                int d = temp[1];
                int mask = temp[2];
                if (d > distance[mask][u]) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0) {
                        int newMask = mask | (1 << v);
                        int newCost = d + cost[u][v];
                        if (newCost < distance[newMask][v]) {
                            distance[newMask][v] = newCost;
                            queue.add(new int[]{v, newCost, newMask});
                        }
                    }
                }
            }
            for(int i = 0 ; i < (1 << n) ; i++){
                System.out.println(Arrays.toString(distance[i]));
            }
            int minCost = Integer.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                if (distance[fullMask][i] != Integer.MAX_VALUE) {
                    int total = distance[fullMask][i] + cost[i][0];
                    minCost = Math.min(minCost, total);
                }
            }
            return minCost;
        }
    }

}
