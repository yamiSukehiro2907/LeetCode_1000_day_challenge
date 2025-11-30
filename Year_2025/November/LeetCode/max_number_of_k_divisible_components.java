import java.util.*;
public class max_number_of_k_divisible_components {
    public static void main(String[] args) {
        /// Link:- [...](https://leetcode.com/problems/maximum-number-of-k-divisible-components/)
    }

    private static class Solution {
        int totalComp;

        public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
            List<Integer>[] adj = new ArrayList[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (var e : edges) {
                int n0 = e[0], n1 = e[1];
                adj[n0].add(n1);
                adj[n1].add(n0);
            }
            totalComp = 0;
            dfs(values, k, adj, 0, -1);
            return totalComp;
        }

        private void dfs(int[] values, int k, List<Integer>[] adj, int currNode, int parent) {
            for (int node : adj[currNode]) {
                if (node != parent) {
                    dfs(values, k, adj, node, currNode);
                    values[currNode] = (values[node] + values[currNode]) % k;
                }
            }
            values[currNode] %= k;
            if (values[currNode] == 0) totalComp++;
        }
    }
    /*
class Solution {
    private int[] values;
    private int divisor;
    private Graph graph;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] v, int k) {
        this.values = v;
        this.divisor = k;
        this.graph = new Graph(n , edges);
        return find(0 , -1)[1];
    }

    private int[] find(int currNode , int parent){
        List<int[]> list = new ArrayList<>();
        for(int node : graph.adjList.get(currNode)) if(node != parent) list.add(find(node , currNode));
        int rem = values[currNode] % divisor;
        if(list.isEmpty()) return rem == 0 ? new int[]{0 , 1} : new int[]{rem , 0};
        int totalComp = 0;
        for(int[] temp : list){
            totalComp += temp[1];
            rem = (temp[0] + rem) % divisor;
        }
        if(rem == 0) return new int[]{0 , totalComp + 1};
        return new int[]{rem , totalComp};
    }

    private static class Graph{
        List<List<Integer>> adjList;
        int vertices;

        public Graph(int vertices , int[][] edges){
            this.adjList = new ArrayList<>();
            this.vertices = vertices;
            init(edges);
        }

        public void init(int[][] edges){
            for(int i = 0 ; i < vertices ; i++) this.adjList.add(new ArrayList<>());
            for(int[] edge : edges){
                this.adjList.get(edge[0]).add(edge[1]);
                this.adjList.get(edge[1]).add(edge[0]);
            }
        }
    }
}
     */
}
