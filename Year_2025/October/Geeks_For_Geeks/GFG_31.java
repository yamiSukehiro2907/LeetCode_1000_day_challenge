
import java.util.*;

public class GFG_31 {

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
                    boolean[] visited = new boolean[vertices];
                    visited[i] = true;
                    int shortestCycleLength = dfs(i, visited, 0, i, -1);
                    System.out.println("Vertices: " + i + " ans: " + shortestCycleLength);
                    if (ans > shortestCycleLength) {
                        ans = shortestCycleLength;
                    }
                }
                return ans == Integer.MAX_VALUE ? -1 : ans;
            }

            private int dfs(int src, boolean[] visited, int distance, int startNode, int parent) {
                int ans = Integer.MAX_VALUE;
                for (int neighbor : adjList.get(src)) {
                    if (neighbor != parent) {
                        int temp = Integer.MAX_VALUE;
                        if (neighbor == startNode && distance > 2) {
                            System.out.println("Got same startNode: " + (distance + 1));
                            temp = Math.min((distance + 1), temp);
                        } else if (!visited[neighbor]) {
                            System.out.println("StartNode: " + startNode + " currNode: " + neighbor + " at distance: " + (distance + 1));
                            visited[neighbor] = true;
                            temp = dfs(neighbor, visited, distance + 1, startNode, src);
                            visited[neighbor] = false;
                        }
                        ans = Math.min(ans, temp);
                    }
                }
                return ans;
            }
        }
    }
}
