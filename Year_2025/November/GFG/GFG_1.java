
import java.util.*;

public class GFG_1 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int[][] edges = {{0, 4}, {2, 4}, {1, 3}, {1, 0}};
        System.out.println(solution.findOrder(n, edges));
    }

    private static class Solution {

        public ArrayList<Integer> findOrder(int n, int[][] prerequisites) {
            Graph graph = new Graph(n, prerequisites);
            return graph.getOrderIfPossible();
        }

        private class Graph {

            private final int vertices;
            private final List<List<Integer>> adjList;
            private final int[][] edges;

            public Graph(int vertices, int[][] edges) {
                this.vertices = vertices;
                this.adjList = new ArrayList<>();
                this.edges = edges;
                initialize();
                fill();
            }

            private void initialize() {
                for (int i = 0; i < vertices; i++) {
                    this.adjList.add(new ArrayList<>());
                }
            }

            private void fill() {
                for (int[] edge : edges) {
                    this.adjList.get(edge[1]).add(edge[0]);
                }
            }

            public ArrayList<Integer> getOrderIfPossible() {
                Queue<Integer> queue = new LinkedList<>();
                int[] indegree = new int[vertices];
                for (int[] edge : edges) {
                    indegree[edge[0]]++;
                }
                for (int i = 0; i < vertices; i++) {
                    if (indegree[i] == 0) {
                        queue.add(i);
                    }
                }
                ArrayList<Integer> order = new ArrayList<>();
                while (!queue.isEmpty()) {
                    int parent = queue.poll();
                    order.add(parent);
                    for (int child : adjList.get(parent)) {
                        indegree[child]--; 
                        if (indegree[child] == 0) {
                            queue.add(child);
                        }
                    }
                }
                if (order.size() != vertices) {
                    return new ArrayList<>();
                }
                return order;
            }
        }
    }
}
