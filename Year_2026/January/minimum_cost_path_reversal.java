package January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class minimum_cost_path_reversal {
    static void main() {

    }

    static class Solution {
        public int minCost(int n, int[][] edges) {
            List<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int[] edge : edges) {
                graph[edge[0]].add(new Edge(edge[1], edge[2]));
                graph[edge[1]].add(new Edge(edge[0], edge[2] * 2));
            }
            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[0] = 0;
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            queue.offer(new int[]{0, 0});
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                if (current[0] == n - 1) return current[1];
                for (Edge edge : graph[current[0]]) {
                    if (distance[edge.node] > current[1] + edge.weight) {
                        distance[edge.node] = current[1] + edge.weight;
                        queue.offer(new int[]{edge.node, distance[edge.node]});
                    }
                }
            }
            return -1;
        }

        static class Edge {
            int node;
            int weight;

            Edge(int node, int weight) {
                this.node = node;
                this.weight = weight;
            }
        }
    }
}
