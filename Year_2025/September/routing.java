import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class routing {
    public static void main(String[] args) {
        Map<String, List<Pair>> map = new HashMap<>();
        Pair p1 = new Pair(7, "R2");
        Pair p2 = new Pair(2, "R3");
        Pair p3 = new Pair(7, "R1");
        Pair p4 = new Pair(3, "R3");
        Pair p5 = new Pair(2, "R1");
        Pair p6 = new Pair(3, "R2");
        map.put("R1", Arrays.asList(p1, p2));
        map.put("R2", Arrays.asList(p3, p4));
        map.put("R3", Arrays.asList(p5, p6));
        String source = "R1";
        System.out.println(Solution.distanceVectorRouting(map, source));
    }

    private static class Pair {
        int distance;
        String next_hop;

        Pair(int distance, String next_hop) {
            this.distance = distance;
            this.next_hop = next_hop;
        }
    }

    private static class Solution {
        public static List<List<String>> distanceVectorRouting(Map<String, List<Pair>> routing, String source) {
            Graph graph = new Graph(routing);
            return graph.getMinPath(getRouter(source));
        }

        private static class Edge {
            int node1;
            int node2;
            int weight;

            Edge(int node1, int node2, int weight) {
                this.node1 = node1;
                this.node2 = node2;
                this.weight = weight;
            }
        }

        private static class Graph {
            private final int size;
            private Edge[] edges;

            public Graph(Map<String, List<Pair>> graph) {
                this.size = graph.size();
                this.edges = create(graph);
            }

            private static Edge[] create(Map<String, List<Pair>> graph) {
                for (String key : graph.keySet()) {
                    for(Pair pair : graph.get(key)){
                        
                    }
                }
                return new Edge[] {};
            }

            private List<List<String>> getMinPath(int source) {
                List<List<String>> ans = new ArrayList<>();
                for (int i = 1; i <= size; i++) {
                    List<String> temp = new ArrayList<>();
                    temp.add("R" + i);
                    temp.add("-1");
                    temp.add("");
                    ans.add(temp);
                }
                for (int i = 1; i <= size; i++) {

                }
                return new ArrayList<>();
            }

        }

        private static int getRouter(String str) {
            int index = 1;
            int num = 0;
            while (index < str.length()) {
                num += (str.charAt(index++) - '0');
            }
            return num;
        }
    }

}
