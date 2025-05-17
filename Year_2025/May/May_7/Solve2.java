package Year_2025.May.May_7;

import java.util.*;

public class Solve2 {
    public static void main(String[] args) {
        int arr[][] = { { 0, 4 }, { 4, 1 } };
        System.out.println(Solution.minTimeToReach(arr));
    }
}

class Solution {
    public static int minTimeToReach(int[][] moveTime) {
        matrix = moveTime;
        Cell start = init();
        Cell end = start.next;
        Queue<Cell> queue = new PriorityQueue<>();
        queue.offer(DUMMY_CELL);
        start.next = null;
        Cell prev = start;
        int currentTime = 0;
        while (true) {
            Cell nextCell = null;
            while (prev != null) {
                for (Cell adj : prev.adjacent) {
                    if (adj.next == adj) {
                        return Math.max(currentTime, end.startTime);
                    } else if (adj.startTime <= currentTime) {
                        adj.next = nextCell;
                        nextCell = adj;
                    } else {
                        adj.next = null;
                        queue.offer(adj);
                    }
                }
                prev = prev.next;
            }
            prev = nextCell;
            int queueTime;
            while ((queueTime = queue.peek().startTime) <= currentTime) {
                Cell entering = queue.poll();
                entering.next = prev;
                prev = entering;
            }
            if (++currentTime < queueTime && prev == null) {
                currentTime = queueTime;
            }
        }
    }

    private static int[][] matrix;
    private static final Cell DUMMY_CELL = new Cell();

    private static Cell init() {
        int m = matrix.length, n = matrix[0].length;
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell(matrix[i][j]);
            }
        }
        Cell[] dummy = new Cell[n];
        Arrays.fill(dummy, DUMMY_CELL);
        Cell[] prevRow = dummy;
        Cell[] currRow = cells[0];
        for (int i = 0; i < m; i++) {
            Cell[] nextRow = (i < m - 1) ? cells[i + 1] : prevRow;
            Cell prev = DUMMY_CELL;
            Cell curr = currRow[0];
            for (int j = 0; j < n; j++) {
                Cell next = (j < n - 1) ? currRow[j + 1] : DUMMY_CELL;
                curr.adjacent = new Cell[] { prev, prevRow[j], next, nextRow[j] };
                prev = curr;
                curr = next;
            }
        }
        Cell start = cells[0][0];
        start.next = cells[m - 1][n - 1];
        return start;
    }

    private static class Cell implements Comparable<Cell> {

        final int startTime;
        Cell[] adjacent;
        Cell next;

        Cell() {
            startTime = Integer.MAX_VALUE;
        }

        Cell(int startTime) {
            this.startTime = startTime;
            next = this;
        }

        @Override
        public int compareTo(Cell other) {
            return this.startTime - other.startTime;
        }
    }
}