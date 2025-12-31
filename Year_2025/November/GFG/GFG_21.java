import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GFG_21 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public int shortestPath(int V, int a, int b, int[][] edges) {
            Graph graph = new Graph(V, edges);
            return graph.findShortestPath(a, b);
        }

        private static class Graph {
            private final List<List<Node>> adjList;
            private final int vertices;

            public Graph(int _vertices, int[][] edges) {
                this.vertices = _vertices;
                this.adjList = new ArrayList<>();
                init(edges);
            }

            private void init(int[][] edges) {
                for (int i = 0; i < vertices; i++) this.adjList.add(new ArrayList<>());
                for (int[] edge : edges) {
                    int u = edge[0], v = edge[1], straight = edge[2], curved = edge[3];
                    this.adjList.get(u).add(new Node(v, straight, curved));
                    this.adjList.get(v).add(new Node(u, straight, curved));
                }
            }

            public int findShortestPath(int src, int dest) {
                if (src == dest) return 0;
                PriorityQueue<NodePair> queue = new PriorityQueue<>((a, b) -> a.totalWeight - b.totalWeight);
                boolean[][] visited = new boolean[vertices][2];
                queue.add(new NodePair(src, 0, false));
                while (!queue.isEmpty()) {
                    NodePair node = queue.poll();
                    if (node.node == dest) return node.totalWeight;
                    int curved = node.curveUsed ? 1 : 0;
                    if (visited[node.node][curved]) continue;
                    visited[node.node][curved] = true;
                    for (Node neighbor : adjList.get(node.node)) {
                        if (!node.curveUsed)
                            queue.add(new NodePair(neighbor.node, node.totalWeight + neighbor.curvedWeight, true));
                        queue.add(new NodePair(neighbor.node, node.totalWeight + neighbor.straightWeight, node.curveUsed));
                    }
                }
                return -1;
            }

            private static class NodePair {
                int node;
                int totalWeight;
                boolean curveUsed;

                public NodePair(int _node, int _totalWeight, boolean _curveUsed) {
                    this.node = _node;
                    this.totalWeight = _totalWeight;
                    this.curveUsed = _curveUsed;
                }
            }
        }

        private static class Node {
            int node;
            int straightWeight;
            int curvedWeight;

            public Node(int _node, int _straightWeight, int _curvedWeight) {
                this.node = _node;
                this.straightWeight = _straightWeight;
                this.curvedWeight = _curvedWeight;
            }
        }
    }
}
