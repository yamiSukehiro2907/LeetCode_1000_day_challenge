import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GFG_20 {
    public static void main(String[] args) {
        String s = "ehhefje";
        String t = "jjjiegh";
        char[][] transform = {{'f', 'e'}, {'i', 'h'}, {'f', 'i'}, {'j', 'i'}, {'g', 'f'}, {'i', 'e'}, {'f', 'f'}, {'e', 'i'}, {'f', 'j'}, {'h', 'j'}};
        int[] cost = {3, 13, 14, 14, 10, 5, 8, 1, 6, 5};
        Solution solution = new Solution();
        System.out.println(solution.minCost(s, t, transform, cost));
    }

    private static class Solution {
        public int minCost(String s, String t, char[][] transform, int[] c) {
            int[][] cost = new int[26][26];
            for (int[] temp : cost) Arrays.fill(temp, Integer.MAX_VALUE);
            for (int i = 0; i < transform.length; i++)
                cost[transform[i][0] - 'a'][transform[i][1] - 'a'] = Math.min(cost[transform[i][0] - 'a'][transform[i][1] - 'a'], c[i]);
            for (int i = 0; i < 26; i++) cost[i][i] = 0;
            int totalCost = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch1 = s.charAt(i);
                char ch2 = t.charAt(i);
                if (ch1 != ch2) {
                    int min = Integer.MAX_VALUE;
                    int minIndex = -1;
                    for (int k = 0; k < 26; k++) {
                        int one_to_this = findShortestPath(cost, ch1, (char) (k + 'a'));
                        int two_to_this = findShortestPath(cost, ch2, (char) (k + 'a'));
                        if (one_to_this != Integer.MAX_VALUE && two_to_this != Integer.MAX_VALUE && min > one_to_this + two_to_this) {
                            min = one_to_this + two_to_this;
                            minIndex = k;
                        }
                    }
                    if (minIndex == -1) return -1;
                    System.out.println(min);
                    totalCost += min;
                    sb.append((char) (minIndex + 'a'));
                } else sb.append(ch1);
            }
            System.out.println(sb);
            return totalCost;
        }

        private int findShortestPath(int[][] cost, char src, char dest) {
            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.totalCost));
            queue.add(new Node(src - 'a', 0));
            boolean[] visited = new boolean[26];
            /// System.out.println("src: " + src + " , dest: " + dest);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (node.node == (dest - 'a')) return node.totalCost;
                if (visited[node.node]) continue;
                visited[node.node] = true;
                /// System.out.println("Node Cost till now: " + node.totalCost);
                for (int i = 0; i < 26; i++)
                    if (node.node != i && cost[node.node][i] != Integer.MAX_VALUE) {
                        /// System.out.println("Reached from " + (char) (node.node + 'a') + " to " + (char) (i + 'a') + " cost: " + (node.totalCost + cost[node.node][i]));
                        queue.add(new Node(i, node.totalCost + cost[node.node][i]));
                    }
            }
            return Integer.MAX_VALUE;
        }

        private static class Node {
            int node;
            int totalCost;

            Node(int _node, int _totalCost) {
                this.node = _node;
                this.totalCost = _totalCost;
            }
        }
    }
}
