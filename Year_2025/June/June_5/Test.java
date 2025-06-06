package Year_2025.June.June_5;

import java.util.*;

class Solution {
    private int totalPath;

    public int countPaths(int[][] edges, int V, int src, int dest) {
        List[] graph = new List[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        System.out.println(graph[src]);
        this.totalPath = 0;
        calculate(graph, src, dest);
        return totalPath;
    }

    private void calculate(List[] graph, int src, int dest) {
        if (src == dest) {
            totalPath = totalPath + 1;
            return;
        }
        for(int adj : (List<Integer>)graph[src]){
            calculate(graph, adj, dest);
        }
    }
}