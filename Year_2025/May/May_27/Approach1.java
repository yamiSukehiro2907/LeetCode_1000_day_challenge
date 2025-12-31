package May.May_27;

import java.util.*;

public class Approach1 {
    static class Solution {
        public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
            int maxNode = getNodeinTree2(edges2, k - 1);
            int n = edges1.length + 1;
            int ans[] = new int[n];
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : edges1) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            for (int i = 0; i < n; i++) {
                boolean[] visited = new boolean[n];
                int temp[] = new int[1];
                temp[0] = 1;
                dfs(graph, visited, k, i, temp);
                ans[i] = temp[0] + maxNode;
            }
            return ans;
        }

        private int getNodeinTree2(int[][] edges, int length) {
            if (length < 0)
                return 0;
            int n = edges.length + 1;
            if (n == 1)
                return 1;
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            int max = 1;
            for (int i = 0; i < n; i++) {
                boolean[] visited = new boolean[n];
                int ans[] = new int[1];
                ans[0] = 1;
                dfs(graph, visited, length, i, ans);
                max = Math.max(max, ans[0]);
            }
            return max;
        }

        private void dfs(List<Integer>[] graph, boolean[] visited, int length, int node, int[] ans) {
            if (length < 0)
                return;
            visited[node] = true;
            if (length > 0) {
                for (int adj : graph[node]) {
                    if (!visited[adj]) {
                        ans[0]++;
                        dfs(graph, visited, length - 1, adj, ans);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}

