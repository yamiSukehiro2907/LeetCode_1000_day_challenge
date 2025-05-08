package Year_2025.May.May_6;

import java.util.PriorityQueue;

public class Solve {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = { { 7, 60, 95 }, { 78, 85, 27 }, { 52, 61, 57 } };
        System.out.println(solution.minTimeToReach(arr));
    }
}

class Solution {
    private int[][] dirs = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    // top , right , bottom , left
    public int minTimeToReach(int[][] moveTime) {
        print(moveTime);
        int m = moveTime.length;
        int n = moveTime[0].length;
        boolean[][] visited = new boolean[m][n];
        int time[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                time[i][j] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Data> queue = new PriorityQueue<>();
        time[0][0] = 0;
        queue.offer(new Data(0, 0, 0));
        while (!queue.isEmpty()) {
            Data temp = queue.poll();
            if (visited[temp.x][temp.y]) {// Already visited coordinate
                continue;
            }
            visited[temp.x][temp.y] = true; // Mark as visited
            for (int[] dir : dirs) {
                int newX = temp.x + dir[0];
                int newY = temp.y + dir[1];
                if (newX >= m || newY >= n || newX < 0 || newY < 0) {
                    continue;
                }
                int distance = Math.max(time[temp.x][temp.y], moveTime[newX][newY]) + 1;
                if (time[newX][newY] > distance) {
                    time[newX][newY] = distance;
                    queue.offer(new Data(newX, newY, distance));
                }
            }
        }
        print(time);
        return time[m - 1][n - 1];
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Data implements Comparable<Data> {
    int x;
    int y;
    int distance;

    Data(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Data Other) {
        return Integer.compare(this.distance, Other.distance);
    }
}