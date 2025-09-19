import java.util.*;

public class temp {
    public static void main(String[] args) {
        int[][] edges = { { 4, 4, 0 },
                { 0, 1, 1 },
                { 1, 2, -3 },
                { 2, 3, 2 },
                { 3, 1, -4 } };
        int nodes = 5;
        int src = 0;
        System.out.println(Arrays.toString(temp.Solution.solve(nodes, edges, src)));
    }

    class Solution {
        public static int[] solve(int nodes, int[][] edges, int src) {
            final int max = 100000000;
            int[] dist = new int[nodes];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < nodes; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
            }
            for (int i = 0; i < nodes - 1; i++) {
                boolean updated = false;
                for (int[] edge : edges) {
                    int u = edge[0], v = edge[1], w = edge[2];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        updated = true;
                    }
                }
                if (!updated) {
                    break;
                }
            }
            Set<Integer> set = new HashSet<>();
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    set.add(v);
                }
            }
            if (!set.isEmpty()) {
                Set<Integer> visited = new HashSet<>();
                Queue<Integer> queue = new LinkedList<>(set);
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    if (visited.contains(node)) {
                        continue;
                    }
                    visited.add(node);
                    dist[node] = max;
                    for (int neighbor : graph.get(node)) {
                        if (!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            for (int i = 0; i < nodes; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    dist[i] = -1;
                }
            }
            return dist;
        }
    }
}