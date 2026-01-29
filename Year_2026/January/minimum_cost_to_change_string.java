package January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class minimum_cost_to_change_string {
    static void main() {

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
            for (int[] row : minCost)
                Arrays.fill(row, Integer.MAX_VALUE);
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for (int i = 0; i < 26; i++) {
                queue.add(new int[]{i, 0});
                minCost[i][i] = 0;
                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    for (int[] next : costs.get(i)) {
                        if (minCost[current[0]][next[0]] > next[1] + current[1]) {
                            minCost[current[0]][next[0]] = next[1] + current[1];
                            queue.offer(new int[]{next[0], minCost[current[0]][next[0]]});
                        }
                    }
                }
            }
        }

        private int find() {
            int totalCost = 0;
            for (int i = 0; i < source.length; i++) {
                if (source[i] != target[i]) {
                    int jumpCost = minCost[source[i] - 'a'][target[i] - 'a'];
                    if (jumpCost == Integer.MAX_VALUE)
                        return -1;
                    totalCost += jumpCost;
                }
            }
            return totalCost;
        }
    }
}
