public class floyd_warshall {
    private final static int unreachable = 100000000;

    public static void main(String[] args) {
        int[][] arr = {
                {0, 4, unreachable, 5, unreachable},
                {unreachable, 0, 1, unreachable, 6},
                {2, unreachable, 0, 3, unreachable},
                {unreachable, unreachable, 1, 0, 2},
                {1, unreachable, unreachable, 4, 0}
        };

        Solution sol = new Solution();
        sol.floydWarshall(arr);
    }

    private static class Solution {

        public void floydWarshall(int[][] dist) {
            print(dist);
            for (int k = 0; k < dist.length; k++) {
                for (int i = 0; i < dist.length; i++) {
                    for (int j = 0; j < dist.length; j++) {
                        if (dist[i][k] == unreachable || dist[k][j] == unreachable) continue;
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
                print(dist);
            }
        }

        private static void print(int[][] dist) {
            for (int[] ints : dist) {
                for (int j = 0; j < dist.length; j++) {
                    System.out.print(ints[j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
