import java.util.*;

public class shortest_path_dag {
    public static void main(String[] args) {
        int[][] edges = {{0, 1, 2,}, {
                0, 4, 1},
                {4, 5, 4},
                {4, 2, 2},
                {1, 2, 3},
                {2, 3, 6},
                {5, 3, 1}};
        int vertices = 6;
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.shortestPath(vertices, edges.length, edges)));
    }

    private static class Solution {
        public int[] shortestPath(int V, int E, int[][] edges) {
            List<List<int[]>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
            int[] distance = new int[V];
            for (int[] edge : edges) adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            Arrays.fill(distance, Integer.MAX_VALUE);
            queue.add(new int[]{0, 0});
            distance[0] = 0;
            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                for (int[] node : adj.get(temp[0])) {
                    int v = node[0];
                    if (distance[v] > temp[1] + node[1]) {
                        distance[v] = temp[1] + node[1];
                        queue.add(new int[]{v, temp[1] + node[1]});
                    }
                }
            }
            for (int i = 0; i < V; i++) if (distance[i] == Integer.MAX_VALUE) distance[i] = 0;
            return distance;
        }
    }
}
