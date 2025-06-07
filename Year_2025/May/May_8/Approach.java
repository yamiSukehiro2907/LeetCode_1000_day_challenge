package Year_2025.May.May_8;

import java.util.*;

public class Approach {

}

class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] time = new int[m][n];
        for (int[] row : time)
            Arrays.fill(row, Integer.MAX_VALUE);
        int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        time[0][0] = 0;
        PriorityQueue<Data> queue = new PriorityQueue<>();
        queue.offer(new Data(0, 0, 0));
        while (!queue.isEmpty()) {
            Data cell = queue.poll();
            if (visited[cell.x][cell.y]) {
                continue; // in case we have visited that cell
            }
            visited[cell.x][cell.y] = true;
            for (int[] dir : dirs) {
                int newX = dir[0] + cell.x;
                int newY = dir[1] + cell.y;
                if (newX < 0 || newX >= m || newY >= n || newY < 0) {
                    continue;
                }
                int distance = Math.max(time[cell.x][cell.y], moveTime[newX][newY]);
                distance += ((cell.x + cell.y) % 2 == 0) ? 1 : 2;
                if (time[newX][newY] > distance) {
                    time[newX][newY] = distance;
                    queue.offer(new Data(newX, newY, distance));
                }
            }
        }
        return time[m - 1][n - 1];
    }

    private class Data implements Comparable<Data> {
        int x;
        int y;
        int distance;

        Data(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Data other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}

class Solution2 {
    private static class Cell implements Comparable<Cell> {
        final int startTime;
        final boolean oneMove;
        Cell[] adjacent;
        Cell next;

        Cell() {
            startTime = Integer.MAX_VALUE;
            oneMove = true;
        }

        Cell(int startTime, boolean oneMove) {
            this.startTime = startTime;
            this.oneMove = oneMove;
            next = this;
        }

        @Override
        public int compareTo(Cell other) {
            return startTime - other.startTime;
        }
    }

    private static final Cell DUMMY_Cell = new Cell();

    private static Cell initCells(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        Cell[][] Cells = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            int[] mtRow = moveTime[i];
            Cell[] rRow = Cells[i];
            for (int j = 0; j < m; j++)
                rRow[j] = new Cell(mtRow[j], ((i + j) & 1) == 0);
        }
        Cell[] dummyRow = new Cell[m];
        Arrays.fill(dummyRow, DUMMY_Cell);
        Cell[] prevRow = dummyRow;
        Cell[] curRow = Cells[0];
        n--;
        m--;
        for (int i = 0; i <= n; i++) {
            Cell[] nextRow = i < n ? Cells[i + 1] : dummyRow;
            Cell prev = DUMMY_Cell;
            Cell cur = curRow[0];
            for (int j = 0; j <= m; j++) {
                Cell next = j < m ? curRow[j + 1] : DUMMY_Cell;
                cur.adjacent = new Cell[] { prev, prevRow[j], next, nextRow[j] };
                prev = cur;
                cur = next;
            }
            prevRow = curRow;
            curRow = nextRow;
        }
        Cell start = Cells[0][0];
        start.next = Cells[n][m];
        return start;
    }

    public static int minTimeToReach(int[][] moveTime) {
        Cell start = initCells(moveTime);
        Cell end = start.next;
        Queue<Cell> queue = new PriorityQueue<>();
        queue.add(DUMMY_Cell);
        start.next = null;
        Cell prev = start;
        Cell curr = null;
        int currTime = 0;
        while (true) {
            Cell currNew = null;
            while (prev != null) {
                for (Cell adj : prev.adjacent)
                    if (adj.next == adj) {
                        if (adj == end)
                            return Math.max(currTime, end.startTime) + (end.oneMove ? 2 : 1);
                        if (adj.startTime <= currTime) {
                            if (adj.oneMove) {
                                adj.next = currNew;
                                currNew = adj;
                            } else {
                                adj.next = curr;
                                curr = adj;
                            }
                        } else {
                            adj.next = null;
                            queue.offer(adj);
                        }
                    }
                prev = prev.next;
            }
            prev = curr;
            curr = currNew;
            int queueTime;
            while ((queueTime = queue.peek().startTime) <= currTime) {
                Cell entering = queue.poll();
                if (entering.oneMove) {
                    entering.next = curr;
                    curr = entering;
                } else {
                    entering.next = prev;
                    prev = entering;
                }
            }
            if (++currTime < queueTime && prev == null && curr == null)
                currTime = queueTime;
        }
    }
}