package January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class minimum_cost_to_change_string {
    static void main() {
        String source = "abcd";
        String target = "acbe";
        char[] original = "abcced".toCharArray();
        char[] changed = "bcbebe".toCharArray();
        int[] cost = {2, 5, 5, 1, 2, 20};
        Solution sol = new Solution();
        System.out.println(sol.minimumCost(source, target, original, changed, cost));
    }

    static class Solution {
        private List<List<int[]>> costs;
        private char[] original, changed;
        private int[] cost;
        private char[] source, target;
        private int[][] minCost;

        public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
            this.source = source.toCharArray();
            this.target = target.toCharArray();
            this.original = original;
            this.changed = changed;
            this.cost = cost;
            createCosts();
            getMinCosts();
            return find();
        }

        private void createCosts() {
            this.costs = new ArrayList<>();
            for (int i = 0; i < 26; i++)
                costs.add(new ArrayList<>());
            for (int i = 0; i < original.length; i++)
                costs.get(original[i] - 'a').add(new int[]{changed[i] - 'a', cost[i]});
        }

        private void getMinCosts() {
            this.minCost = new int[26][26];
            for (int[] row : minCost) Arrays.fill(row, Integer.MAX_VALUE);
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for (int i = 0; i < 26; i++) {
                queue.add(new int[]{i, 0});
                minCost[i][i] = 0;
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    if (current[1] > minCost[i][current[0]]) continue;
                    for (int[] next : costs.get(current[0])) {
                        if (minCost[i][next[0]] > next[1] + current[1]) {
                            minCost[i][next[0]] = next[1] + current[1];
                            queue.offer(new int[]{next[0], minCost[i][next[0]]});
                        }
                    }
                }
            }

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    System.out.println("From: " + (char) (i + 'a') + " To: " + (char) (j + 'a') + ": " + minCost[i][j]);
                }
            }
        }

        private int find() {
            int totalCost = 0;
            for (int i = 0; i < source.length; i++) {
                System.out.println("source: " + source[i] + " target: " + target[i]);
                if (source[i] != target[i]) {
                    int jumpCost = minCost[source[i] - 'a'][target[i] - 'a'];
                    if (jumpCost == Integer.MAX_VALUE)
                        return -1;
                    System.out.println(jumpCost);
                    totalCost += jumpCost;
                }
            }
            return totalCost;
        }
    }

    static class Solution2 {
        public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
            int[][] dis = new int[26][26];
            for (int i = 0; i < 26; i++) {
                Arrays.fill(dis[i], Integer.MAX_VALUE);
                dis[i][i] = 0;
            }
            for (int i = 0; i < cost.length; i++) {
                dis[original[i] - 'a'][changed[i] - 'a'] = Math.min(dis[original[i] - 'a'][changed[i] - 'a'], cost[i]);
            }
            for (int k = 0; k < 26; k++) {
                for (int i = 0; i < 26; i++) {
                    if (dis[i][k] < Integer.MAX_VALUE) {
                        for (int j = 0; j < 26; j++) {
                            if (dis[k][j] < Integer.MAX_VALUE) dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                        }
                    }
                }
            }

            long ans = 0L;
            for (int i = 0; i < source.length(); i++) {
                int c1 = source.charAt(i) - 'a';
                int c2 = target.charAt(i) - 'a';
                if (dis[c1][c2] == Integer.MAX_VALUE) return -1L;
                ans += (long) dis[c1][c2];
            }
            return ans;
        }
    }
}
