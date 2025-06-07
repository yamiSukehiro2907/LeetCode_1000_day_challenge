package May.May_29;

import java.util.*;

public class Approach1 {
    public static void main(String[] args) {
        int[] edges = { 1, 2 - 1 };
        int node1 = 0;
        int node2 = 2;
        System.out.println(Solution.closestMeetingNode(edges, node1, node2));
    }

    class Solution {
        private static int NodeCount;

        private static boolean inRange(int n) {
            return n >= 0 && n < NodeCount;
        }

        public static int closestMeetingNode(int[] edges, int node1, int node2) {
            NodeCount = edges.length;
            if (!inRange(node2) || !inRange(node1)) {
                return -1;
            }
            if (node1 == node2) {
                return 0;
            }
            int[] distanceFromNode1 = getDistance(edges, node1);
            int[] distanceFromNode2 = getDistance(edges, node2);
            print(distanceFromNode1);
            print(distanceFromNode2);
            int ans = Integer.MAX_VALUE;
            int node = -1;
            for (int i = 0; i < edges.length; i++) {
                if (distanceFromNode1[i] == -1 || distanceFromNode2[i] == -1) {
                    continue;
                }
                int possible = Math.max(distanceFromNode1[i], distanceFromNode2[i]);
                if (ans > possible) {
                    node = i;
                    ans = possible;
                }
            }
            return node;
        }

        private static int[] getDistance(int[] edges, int node1) {
            int distance[] = new int[edges.length];
            Arrays.fill(distance, -1);
            boolean[] visited = new boolean[edges.length];
            int currNode = node1;
            visited[currNode] = true;
            int dist = 0;
            while (inRange(currNode) && inRange(edges[currNode]) && !visited[edges[currNode]]) {
                dist++; // we reached
                currNode = edges[currNode];
                distance[currNode] = dist;
                visited[currNode] = true;
            }
            return distance;
        }

        private static void print(int[] edges) {
            for (int num : edges) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}