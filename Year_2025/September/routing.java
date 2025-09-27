import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        int cost;
        String dest;

        Pair(int distance, String next_hop) {
            this.cost = distance;
            this.dest = next_hop;
        }
    }

    private static class Solution {
        public static List<List<String>> distanceVectorRouting(Map<String, List<Pair>> map, String source) {
            Graph graph = new Graph(map);
            return graph.getMinPath(source);
        }

        private static class Graph {
            private final List<Edge> edges;
            private List<String> routers;
            private final Map<String, List<Pair>> map;

            private class Edge {
                String node1;
                String node2;
                int cost;

                Edge(String node1, String node2, int cost) {
                    this.node1 = node1;
                    this.node2 = node2;
                    this.cost = cost;
                }
            }

            public Graph(Map<String, List<Pair>> map) {
                this.map = map;
                this.edges = new ArrayList<>();
                getRouters();
                getEdges();
            }

            private void getRouters() {
                Set<String> set = new HashSet<>();
                for (String key : map.keySet()) {
                    set.add(key);
                    for (Pair pair : map.get(key)) {
                        set.add(pair.dest);
                    }
                }
                this.routers = new ArrayList<>(set);
                Collections.sort(this.routers);
            }

            private void getEdges() {
                for (String router : routers) {
                    if (map.containsKey(router)) {
                        for (Pair pair : map.get(router)) {
                            System.out.println(router + " -> " + pair.dest + " (" + pair.cost + ")");
                            edges.add(new Edge(router, pair.dest, pair.cost));
                        }
                    }
                }
            }

            public List<List<String>> getMinPath(String source) {
                int size = routers.size();
                int[] distance = new int[size];
                String[] nextHop = new String[size];
                Arrays.fill(distance, Integer.MAX_VALUE);
                Arrays.fill(nextHop, "");
                distance[getRouter(source) - 1] = 0;
                for (int i = 0; i < size; i++) {
                    for (Edge edge : edges) {
                        String node1 = edge.node1;
                        String node2 = edge.node2;
                        int cost = edge.cost;
                        int node1Index = getRouter(node1) - 1;
                        int node2Index = getRouter(node2) - 1;
                        if (distance[node1Index] != Integer.MAX_VALUE
                                && distance[node2Index] > distance[node1Index] + cost) {
                            distance[node2Index] = distance[node1Index] + cost;
                            if (node1.equals(source)) {
                                System.out.println(node2);
                                nextHop[node2Index] = node2;
                            } else {
                                nextHop[node2Index] = nextHop[node1Index];
                            }
                        }
                        System.out.println(Arrays.toString(distance));
                    }
                }
                List<List<String>> ans = new ArrayList();
                for (int i = 0; i < size; i++) {
                    List<String> temp = new ArrayList<>();
                    temp.add("R" + (i + 1));
                    temp.add(Integer.toString(distance[i] == Integer.MAX_VALUE ? -1 : distance[i]));
                    temp.add(distance[i] == Integer.MAX_VALUE ? "" : nextHop[i]);
                    ans.add(temp);
                }
                return ans;
            }

            private int getRouter(String str) {
                int index = 1;
                int num = 0;
                while (index < str.length()) {
                    num *= 10;
                    num += str.charAt(index) - '0';
                    index++;
                }
                return num;
            }
        }
    }

}
