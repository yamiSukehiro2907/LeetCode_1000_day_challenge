
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GFG_31_optimized {

    public static void main(String[] args) {
        int vertices = 7;
        int[][] edges = {{0, 5}, {0, 6}, {5, 1}, {6, 1}, {6, 2}, {2, 3}, {3, 4}, {1, 4}};
        Solution solution = new Solution();
        System.out.println(solution.shortCycle(vertices, edges));
    }

    private static class Solution {

        public int shortCycle(int V, int[][] edges) {
            Graph graph = new Graph(V, edges);
            return graph.getShortestCycleLength();
        }

        private class Graph {

            private final List<List<Integer>> adjList;
            private final int vertices;

            public Graph(int vertices, int[][] edges) {
                this.vertices = vertices;
                this.adjList = new ArrayList<>();
                initialize();
                fill(edges);
            }

            private void initialize() {
                for (int i = 0; i < vertices; i++) {
                    this.adjList.add(new ArrayList<>());
                }
            }

            private void fill(int[][] edges) {
                for (int[] edge : edges) {
                    adjList.get(edge[0]).add(edge[1]);
                    adjList.get(edge[1]).add(edge[0]);
                }
            }

            private int getShortestCycleLength() {
                int ans = Integer.MAX_VALUE;
                for (int i = 0; i < vertices; i++) {
                    int minLength = find(i);
                    if (minLength < ans) {
                        ans = minLength;
                    }
                }
                return ans == Integer.MAX_VALUE ? -1 : ans;
            }

            private int find(int start) {
                int[] distance = new int[vertices];
                int[] parent = new int[vertices];
                Arrays.fill(distance, -1);
                Arrays.fill(parent, -1);
                distance[start] = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(start);
                int ans = Integer.MAX_VALUE;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : adjList.get(node)) {
                        if (distance[neighbor] == -1) {
                            distance[neighbor] = distance[node] + 1;
                            parent[neighbor] = node;
                            queue.add(neighbor);
                        } else if (parent[node] != neighbor) {
                            ans = Math.min(ans, distance[node] + distance[neighbor] + 1);
                        }
                    }
                }
                return ans;
            }
        }
    }
}
