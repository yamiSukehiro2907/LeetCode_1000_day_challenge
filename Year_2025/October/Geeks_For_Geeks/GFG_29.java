
import java.util.*;

public class GFG_29 {

    public static void main(String[] args) {
        int V = 6;
        int[][] edges = {{0, 1}, {0, 4}, {1, 3}, {1, 2}, {2, 5}};
        Solution solution = new Solution();
        System.out.println(solution.diameter(V, edges));
    }

    private static class Solution {

        public int diameter(int V, int[][] edges) {
            Graph graph = new Graph(V, edges);
            return graph.getDiameter();
        }

        private class Graph {

            private final List<List<Integer>> graph;
            private final int vertices;

            public Graph(int vertices, int[][] edges) {
                this.graph = new ArrayList<>();
                this.vertices = vertices;
                initialize();
                fill(edges);
            }

            public int getDiameter() {
                Map<Integer, TreeData> map = new HashMap<>();
                int diameter = 0;
                for (int i = 0; i < vertices; i++) {
                    if (!map.containsKey(i)) {
                        findDiameter(i, i, map);
                    }
                    int d = map.get(i).diameter;
                    if (d > diameter) {
                        diameter = d;
                    }
                }
                return diameter - 1;
            }

            private void findDiameter(int src, int parent, Map<Integer, TreeData> map) {
                List<TreeData> neighborsData = new ArrayList<>();
                for (int neighbor : graph.get(src)) {
                    if (parent != neighbor) {
                        if (!map.containsKey(neighbor)) {
                            findDiameter(neighbor, src, map);
                        }
                        neighborsData.add(map.get(neighbor));
                    }
                }
                TreeData data;
                if (neighborsData.isEmpty()) {
                    data = new TreeData(1, 1);
                } else {
                    Collections.sort(neighborsData, (a, b) -> b.height - a.height);
                    int maxheight1 = neighborsData.get(0).height;
                    int maxheight2 = (neighborsData.size() > 1) ? neighborsData.get(1).height : 0;
                    int maxDiameter = neighborsData.get(0).diameter;
                    for (TreeData temp : neighborsData) {
                        if (temp.diameter > maxDiameter) {
                            maxDiameter = temp.diameter;
                        }
                    }
                    data = new TreeData(maxheight1 + 1, Math.max(maxDiameter, maxheight1 + maxheight2 + 1));
                }
                map.put(src, data);
            }

            private void initialize() {
                for (int i = 0; i < vertices; i++) {
                    this.graph.add(new ArrayList<>());
                }
            }

            private void fill(int[][] edges) {
                for (int[] edge : edges) {
                    graph.get(edge[0]).add(edge[1]);
                    graph.get(edge[1]).add(edge[0]);
                }
            }

            private class TreeData {

                int height;
                int diameter;

                public TreeData(int height, int diameter) {
                    this.height = height;
                    this.diameter = diameter;
                }
            }
        }
    }
}

