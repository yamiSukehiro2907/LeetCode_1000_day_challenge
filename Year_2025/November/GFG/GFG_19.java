import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GFG_19 {
    public static void main(String[] args) {
        int[][] matrix = {{7, 2, 6, 5}, {3, 1, 10, 8}};
        Solution solution = new Solution();
        System.out.println(solution.minCostPath(matrix));
    }

    private static class Solution {
        public int minCostPath(int[][] mat) {
            Graph graph = new Graph(mat);
            return graph.findMinCostPath();
        }

        private static class Graph {
            private final List<List<Node>> list;
            private final static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            private final int[][] matrix;

            public Graph(int[][] mat) {
                this.list = new ArrayList<>();
                this.matrix = mat;
                init();
            }

            public int findMinCostPath() {
                PriorityQueue<NodePair> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.maxHop));
                queue.add(new NodePair(0, 0));
                boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                while (!queue.isEmpty()) {
                    NodePair node = queue.poll();
                    int x1 = node.node / matrix[0].length;
                    int y1 = node.node % matrix[0].length;
                    if (visited[x1][y1]) continue;
                    visited[x1][y1] = true;
                    if (x1 == matrix.length - 1 && y1 == matrix[0].length - 1) return node.maxHop;
                    for (Node neighbor : list.get(node.node)) {
                        int x = neighbor.node / matrix[0].length;
                        int y = neighbor.node % matrix[0].length;
                        if (!visited[x][y]) {
                            queue.add(new NodePair(neighbor.node, Math.max(node.maxHop, neighbor.weight)));
                        }
                    }
                }
                return -1;
            }

            private void init() {
                for (int i = 0; i < matrix.length * matrix[0].length; i++) list.add(new ArrayList<>());
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        for (int[] dir : directions) {
                            int newX = dir[0] + i;
                            int newY = dir[1] + j;
                            int from = i * matrix[0].length + j;
                            if (newX >= 0 && newY >= 0 && newX < matrix.length && newY < matrix[0].length) {
                                int weight = Math.abs(matrix[newX][newY] - matrix[i][j]);
                                int to = newX * matrix[0].length + newY;
                                list.get(from).add(new Node(to, weight));
                            }
                        }
                    }
                }
            }

            private static class Node {
                int node;
                int weight;

                public Node(int node, int weight) {
                    this.node = node;
                    this.weight = weight;
                }
            }

            private static class NodePair {
                int node;
                int maxHop;

                public NodePair(int node, int maxHop) {
                    this.node = node;
                    this.maxHop = maxHop;
                }
            }
        }
    }

}
