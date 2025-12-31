import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GFG_22 {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}};
        Solution solution = new Solution();
        System.out.println(solution.minConnect(n, edges));
    }

    private static class Solution {
        private List<List<Integer>> adjList;

        public int minConnect(int vertices, int[][] edges) {
            if (edges.length < vertices - 1) return -1;
            int[] clusterMapping = new int[vertices];
            Arrays.fill(clusterMapping, -1);
            this.adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) this.adjList.add(new ArrayList<>());
            for (int[] edge : edges) {
                this.adjList.get(edge[0]).add(edge[1]);
                this.adjList.get(edge[1]).add(edge[0]);
            }
            int clusterCount = 0;
            int totalExtraEdge = 0;
            for (int i = 0; i < vertices; i++) {
                if (clusterMapping[i] == -1) {
                    clusterCount++;
                    int[] temp = new int[2];
                    dfs(temp, clusterMapping, i, clusterCount);
                    System.out.println("temp: " + Arrays.toString(temp));
                    if (temp[1] >= temp[0]) totalExtraEdge += (temp[1] - temp[0] + 1);
                }
            }
            System.out.println("clusterMapping: " + Arrays.toString(clusterMapping));
            System.out.println("Cluster count: " + clusterCount);
            System.out.println("Total extra edge: " + totalExtraEdge);
            if (clusterCount - 1 > totalExtraEdge) return -1;
            return clusterCount - 1;
        }

        private void dfs(int[] cluster, int[] clusterMapping, int src, int clusterNumber) {
            clusterMapping[src] = clusterNumber;
            cluster[0]++; // number of points
            for (int node : adjList.get(src)) {
                cluster[1]++; // number of edges
                if (clusterMapping[node] == -1) {
                    dfs(cluster, clusterMapping, node, clusterNumber);
                }
            }
        }
    }

}
