package Year_2025.May.May_28;

import java.util.*;

public class Approach {
    class Solution {
        public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
            final int[] tree1 = calculateTarget(edges1);
            final int bonus = findMax(calculateTarget(edges2));
            for (int i = 0; i < tree1.length; i++) {
                tree1[i] += bonus;
            }
            return tree1;
        }

        static final int findMax(int[] arr) {
            int max = 0;
            for (int val : arr) {
                max = Math.max(max, val);
            }
            return max;
        }

        static int[] calculateTarget(int[][] edges) {
            final int n = edges.length + 1;
            final int[] deg = new int[n];
            final int[] parent = new int[n];
            final int[] count = new int[n];
            Arrays.fill(count, 1);
            final int[] queue = new int[n];
            for (int[] edge : edges) {
                final int u = edge[0];
                final int v = edge[1];
                deg[u]++;
                deg[v]++;
                parent[u] ^= v;
                parent[v] ^= u;
            }
            int size = 0;
            for (int i = 0; i < n; i++) {
                if (deg[i] == 1) {
                    queue[size++] = i;
                }
            }
            for (int i = 0; i < edges.length; i++) {
                final int node = queue[i];
                final int par = parent[node];
                parent[par] ^= node;
                count[par] -= count[node];
                if (--deg[par] == 1) {
                    queue[size++] = par;
                }
            }
            final int root = queue[n - 1];
            count[root] = (n + count[root]) / 2;
            for (int i = n - 2; i >= 0; i--) {
                final int node = queue[i];
                count[node] = n - count[parent[node]];
            }
            return count;
        }
    }
}
