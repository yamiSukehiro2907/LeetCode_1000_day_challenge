package March;

import java.util.*;

public class number_of_ways_to_arrive_at_destination {
    static void main() {

    }

    static class Solution {
        public int countPaths(int V, int[][] edges) {
            List<List<int[]>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
            for (int[] edge : edges) {
                adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
                adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
            }
            int[] dist = new int[V];
            int[] ways = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            ways[0] = 1;
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            queue.offer(new int[]{0, 0});
            while (!queue.isEmpty()) {
                int[] node = queue.poll();
                int u = node[0], d = node[1];
                if (d > dist[u]) continue;
                for (int[] next : adj.get(u)) {
                    int v = next[0], w = next[1];
                    int newDist = d + w;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        ways[v] = ways[u];
                        queue.offer(new int[]{v, newDist});
                    } else if (newDist == dist[v]) ways[v] += ways[u];
                }
            }
            return ways[V - 1];
        }
    }
}
