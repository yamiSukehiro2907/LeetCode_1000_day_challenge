import java.util.ArrayList;
import java.util.List;

public class GFG_23 {
    public static void main(String[] args) {

    }

    private static class Solution {
        List<List<Integer>> rows;
        List<List<Integer>> cols;

        int maxRemove(int[][] stones) {
            ///  initialize the max indexes they can hold
            this.rows = new ArrayList<>();
            this.cols = new ArrayList<>();
            for (int i = 0; i < 10001; i++) {
                rows.add(new ArrayList<>());
                cols.add(new ArrayList<>());
            }
            for (int i = 0; i < stones.length; i++) {
                int[] stone = stones[i];
                /// for each stone add it's mapping to its rows and cols
                int x = stone[0], y = stone[1];
                rows.get(x).add(i);
                cols.get(y).add(i);
            }
            int totalCount = 0;
            boolean[] visited = new boolean[stones.length];
            /// if a node is not part of any cluster , find count in that cluster and totalStones that can be removed is totalNodes in that cluster - 1
            for (int i = 0; i < stones.length; i++) if (!visited[i]) totalCount += (findNodes(stones, i, visited) - 1);
            return totalCount;
        }

        private int findNodes(int[][] stones, int index, boolean[] visited) {
            visited[index] = true;
            int count = 1;
            int x = stones[index][0];
            int y = stones[index][1];
            /// keep on finding stones that belong to this cluster
            for (int point : rows.get(x)) if (!visited[point]) count += findNodes(stones, point, visited);
            for (int point : cols.get(y)) if (!visited[point]) count += findNodes(stones, point, visited);
            return count;
        }
    }

    ;
}
