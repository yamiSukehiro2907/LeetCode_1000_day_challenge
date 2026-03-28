package March;

import java.util.ArrayList;
import java.util.List;

public class articulation_point_2 {
    static void main() {

    }

    static class Solution {
        private static ArrayList<Integer> ans;
        private static boolean[] visited;
        private static int timer;
        private static int[] lowest;
        private static int[] first;
        private static List<List<Integer>> adjList;
        private static boolean[] isAP;

        static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
            ans = new ArrayList<>();
            visited = new boolean[V];
            lowest = new int[V];
            first = new int[V];
            timer = 0;
            adjList = new ArrayList<>();
            isAP = new boolean[V];
            for (int i = 0; i < V; i++) adjList.add(new ArrayList<>());
            for (int[] edge : edges) {
                adjList.get(edge[0]).add(edge[1]);
                adjList.get(edge[1]).add(edge[0]);
            }
            for (int i = 0; i < V; i++) if (!visited[i]) dfs(i, -1);
            for (int i = 0; i < V; i++) if (isAP[i]) ans.add(i);
            if (ans.isEmpty()) ans.add(-1);
            return ans;
        }

        private static void dfs(int src, int parent) {
            visited[src] = true;
            lowest[src] = first[src] = timer++;
            int children = 0;
            for (int next : adjList.get(src)) {
                if (!visited[next]) {
                    children++;
                    dfs(next, src);
                    lowest[src] = Math.min(lowest[src], lowest[next]);
                    if (parent == -1 && children > 1) isAP[src] = true;
                    if (parent != -1 && lowest[next] >= first[src]) isAP[src] = true;
                } else {
                    lowest[src] = Math.min(lowest[src], first[next]);
                }
            }
        }
    }
}
