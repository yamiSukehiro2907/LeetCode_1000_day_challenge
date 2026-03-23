package March;

import java.util.ArrayList;
import java.util.List;

public class longest_cycle_in_directed {
    static void main() {

    }

    static class Solution {

        private List<List<Integer>> adjList;
        private int[] visited;
        private int[] depth;
        private int max = -1;

        public int longestCycle(int V, int[][] edges) {
            this.adjList = new ArrayList<>();
            this.visited = new int[V];
            this.depth = new int[V];
            for (int i = 0; i < V; i++) this.adjList.add(new ArrayList<>());
            for (int[] edge : edges) adjList.get(edge[0]).add(edge[1]);
            for (int i = 0; i < V; i++) if (visited[i] == 0) solve(i, 0);
            return max;
        }

        private void solve(int src, int count) {
            visited[src] = 2;
            depth[src] = count;
            for (int next : adjList.get(src)) {
                if (visited[next] == 2) max = Math.max(max, count - depth[next] + 1);
                if (visited[next] == 0) solve(next, count + 1);
            }
            visited[src] = 1;
        }
    }
}
