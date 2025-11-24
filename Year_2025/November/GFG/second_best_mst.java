import java.util.Arrays;
import java.util.Comparator;

public class second_best_mst {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges = {{0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 5}, {2, 4, 10}, {2, 3, 7}, {3, 4, 2}};
        System.out.println(s.secondMST(5, edges));
    }
/// MST := Tree connecting all vertices with minimum total Weight
    private static class Solution {
        public int secondMST(int V, int[][] edges) {
            Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
            boolean[] visited = new boolean[edges.length];
            UnionFind unionfind = new UnionFind(V);
            /// created the first MST
            int[] usedEdge = new int[V - 1];
            int index = 0, totalWeight = 0;
            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0], v = edges[i][1];
                if (unionfind.find(u) != unionfind.find(v)) {
                    unionfind.union(u, v);
                    visited[i] = true;
                    usedEdge[index++] = i;
                    totalWeight += edges[i][2];
                }
            }
            if (index != V - 1) return -1;
            int secondMST = Integer.MAX_VALUE;
            for (int edgeIndex : usedEdge) {
                UnionFind newMST = new UnionFind(V);
                /// create new MST in which replace one of connected edge and find its replacement
                int tempWeight = 0, edgeCount = 0;
                for (int temp : usedEdge) {
                    if (temp != edgeIndex) {
                        int[] edge = edges[temp];
                        if (newMST.find(edge[0]) != newMST.find(edge[1])) {
                            newMST.union(edge[0], edge[1]);
                            tempWeight += edge[2];
                            edgeCount++;
                        }
                    }
                }
                int start = 0;
                while (start < edges.length) {
                    if (!visited[start]) {
                        int[] edge = edges[start];
                        if (newMST.find(edge[0]) != newMST.find(edge[1])) {
                            newMST.union(edge[0], edge[1]);
                            tempWeight += edge[2];
                            edgeCount++;
                        }
                    }
                    start++;
                }
                /// total edgeCount should be V - 1 and secondMST's weight > totalWeight
                if (edgeCount == V - 1 && tempWeight > totalWeight && tempWeight < secondMST) secondMST = tempWeight;
            }
            return secondMST == Integer.MAX_VALUE ? -1 : secondMST;
        }

        private static class UnionFind {
            private final int[] parent;

            UnionFind(int size) {
                this.parent = new int[size];
                for (int i = 0; i < size; i++) this.parent[i] = i;
            }

            public int find(int i) {
                if (parent[i] == i) return i;
                return find(parent[i]);
            }

            public void union(int i, int j) {
                int iRep = find(i);
                int jRep = find(j);
                parent[iRep] = jRep;
            }
        }
    }
}
