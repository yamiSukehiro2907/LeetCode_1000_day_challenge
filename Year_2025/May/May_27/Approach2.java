package May.May_27;

import java.util.*;

public class Approach2 {
    public static void main(String[] args) {

    }

    static class Solution {
        List<Integer>[] buildGraph(int[][] edges, int nodeCount) {
            List<Integer>[] graph = new List[nodeCount];
            for (int[] edge : edges) {
                int nodeA = edge[0];
                int nodeB = edge[1];
                if (graph[nodeA] == null)
                    graph[nodeA] = new ArrayList<>();
                if (graph[nodeB] == null)
                    graph[nodeB] = new ArrayList<>();
                graph[nodeA].add(nodeB);
                graph[nodeB].add(nodeA);
            }
            return graph;
        }

        public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
            int tree1Size = edges1.length + 1;
            int tree2Size = edges2.length + 1;
            if (k == 0) {
                int[] result = new int[tree1Size];
                Arrays.fill(result, 1);
                return result;
            }
            List<Integer>[] tree1Graph = buildGraph(edges1, tree1Size);
            List<Integer>[] tree2Graph = buildGraph(edges2, tree2Size);
            int[][] tree1DownwardReach = new int[tree1Size][k + 1];
            int[][] tree2DownwardReach = new int[tree2Size][k];
            int[] tree1Parents = new int[tree1Size];
            int[] tree2Parents = new int[tree2Size];
            int[] tree1TotalCounts = new int[tree1Size];
            int[] tree2TotalCounts = new int[tree2Size];
            Queue<Integer> tree1ProcessOrder = new LinkedList<>();
            Queue<Integer> tree2ProcessOrder = new LinkedList<>();
            dfs(0, -1, tree1Graph, tree1Parents, tree1DownwardReach, tree1ProcessOrder, k);
            dfs(0, -1, tree2Graph, tree2Parents, tree2DownwardReach, tree2ProcessOrder, k - 1);
            calculateTotalReachability(tree1ProcessOrder, tree1Parents, tree1DownwardReach, tree1TotalCounts, k);
            calculateTotalReachability(tree2ProcessOrder, tree2Parents, tree2DownwardReach, tree2TotalCounts, k - 1);
            int maxTree2Connectivity = Arrays.stream(tree2TotalCounts).max().orElse(1);
            for (int nodeIndex = 0; nodeIndex < tree1Size; nodeIndex++) {
                tree1TotalCounts[nodeIndex] += maxTree2Connectivity;
            }
            return tree1TotalCounts;
        }

        void dfs(int currentNode, int parentNode, List<Integer>[] graph, int[] parents, int[][] downwardReach,
                Queue<Integer> processOrder, int maxDistance) {
            downwardReach[currentNode][0] = 1;
            processOrder.add(currentNode);
            parents[currentNode] = parentNode;
            if (graph[currentNode] != null) {
                for (int neighbor : graph[currentNode]) {
                    if (neighbor != parentNode) {
                        dfs(neighbor, currentNode, graph, parents, downwardReach, processOrder, maxDistance);
                    }
                }
            }
            if (parentNode != -1) {
                for (int distance = 1; distance <= maxDistance; distance++) {
                    downwardReach[parentNode][distance] += downwardReach[currentNode][distance - 1];
                }
            }
        }

        void calculateTotalReachability(Queue<Integer> processOrder, int[] parents, int[][] downwardReach,
                int[] totalCounts, int maxDistance) {
            int nodeCount = downwardReach.length;
            int maxDistancePlus1 = maxDistance + 1;
            int[][] upwardReach = new int[nodeCount][maxDistancePlus1];
            while (!processOrder.isEmpty()) {
                int currentNode = processOrder.poll();
                int parentNode = parents[currentNode];
                totalCounts[currentNode] = downwardReach[currentNode][0];
                if (maxDistance >= 1) {
                    if (parentNode != -1) {
                        upwardReach[currentNode][1] = 1;
                    }
                    totalCounts[currentNode] += downwardReach[currentNode][1] + upwardReach[currentNode][1];
                }
                for (int distance = 2; distance <= maxDistance; distance++) {
                    if (parentNode != -1) {
                        upwardReach[currentNode][distance] = downwardReach[parentNode][distance - 1] -
                                downwardReach[currentNode][distance - 2] +
                                upwardReach[parentNode][distance - 1];
                    }
                    totalCounts[currentNode] += downwardReach[currentNode][distance]
                            + upwardReach[currentNode][distance];
                }
            }
        }
    }
}
