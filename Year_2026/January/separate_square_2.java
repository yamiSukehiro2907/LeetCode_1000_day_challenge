package January;

import java.util.Arrays;
import java.util.Comparator;

public class separate_square_2 {
    static void main() {

    }

    static class Solution {

        private static final class Event {
            final long yLine;
            final int left, right;
            final int delta;

            public Event(long yLine, int left, int right, int delta) {
                this.yLine = yLine;
                this.left = left;
                this.right = right;
                this.delta = delta;
            }
        }

        private static final class SegmentTree {
            final long[] xs;
            final long[] cover;
            final long[] count;

            public SegmentTree(long[] xs) {
                this.xs = xs;
                int n = Math.max(1, xs.length - 1);
                this.cover = new long[n << 2];
                this.count = new long[n << 2];
            }

            public long covered() {
                return cover[1];
            }

            public void update(int left, int right, int delta) {
                if (left >= right) return;
                update(1, 0, xs.length - 1, left, right, delta);
            }

            public void update(int node, int left, int right, int queryLeft, int queryRight, int delta) {
                if (queryRight <= left || right <= queryLeft) return;
                if (queryLeft <= left && right <= queryRight) {
                    count[node] += delta;
                    pushUp(node, left, right);
                    return;
                }
                int mid = (left + right) >>> 1;
                update(node << 1, left, mid, queryLeft, queryRight, delta);
                update(node << 1 | 1, mid, right, queryLeft, queryRight, delta);
                pushUp(node, left, right);
            }

            private void pushUp(int node, int left, int right) {
                if (count[node] > 0) cover[node] = xs[right] - xs[left];
                else if (left + 1 == right) cover[node] = 0;
                else cover[node] = cover[node << 1] + cover[node << 1 | 1];
            }
        }

        public double separateSquares(int[][] squares) {
            int n = squares.length;
            if (n == 0) return -1;

            long[] xs = new long[2 * n];
            int index = 0;
            for (int[] square : squares) {
                long xStart = square[0], xEnd = square[0] + square[2];
                xs[index++] = xStart;
                xs[index++] = xEnd;
            }
            Arrays.sort(xs);
            index = 1;
            for (int i = 1; i < xs.length; i++) {
                // removing duplicates
                if (xs[i] != xs[index - 1]) xs[index++] = xs[i];
            }

            xs = Arrays.copyOf(xs, index); // take the unique boundaries of xStart and xEnd
            if (xs.length < 2) { // no squares
                long minY = Long.MAX_VALUE;
                for (int[] s : squares) minY = Math.min(minY, s[1]);
                return (double) minY;
            }

            Event[] events = new Event[2 * n];
            index = 0;
            for (int[] square : squares) {
                long xStart = square[0], xEnd = square[0] + square[2];
                long yStart = square[1], yEnd = square[1] + square[2];
                int left = lowerBound(xs, xStart);
                int right = lowerBound(xs, xEnd);
                if (left < right) {
                    events[index++] = new Event(yStart, left, right, 1);
                    events[index++] = new Event(yEnd, left, right, -1);
                }
            }

            if (index == 0) return -1;
            events = Arrays.copyOf(events, index);
            Arrays.sort(events, Comparator.comparingLong(a -> a.yLine));

            SegmentTree segmentTree = new SegmentTree(xs);

            long[] yStart = new long[index];
            long[] yEnd = new long[index];
            long[] base = new long[index];

            int gIndex = 0;

            long area = 0;
            long prevY = events[0].yLine;
            long baseLine = 0;
            index = 0;
            while (index < events.length) {
                long currY = events[index].yLine;
                long dy = currY - prevY;

                if (dy != 0 && baseLine != 0) {
                    area += baseLine * dy;
                    yStart[gIndex] = prevY;
                    yEnd[gIndex] = currY;
                    base[gIndex] = baseLine;
                    gIndex++;
                }
                int j = index;
                while (j < events.length && events[j].yLine == currY) {
                    segmentTree.update(events[j].left, events[j].right, events[j].delta);
                    j++;
                }
                baseLine = segmentTree.covered();
                prevY = currY;
                index = j;
            }

            if (area == 0) return prevY;

            double target = area / 2.0;
            long frontArea = 0;
            for (int i = 0; i < gIndex; i++) {
                long temp = base[i] * (yEnd[i] - yStart[i]);
                if (frontArea + temp < target) frontArea += temp;
                else {
                    double neededArea = target - frontArea;
                    return yStart[i] + (neededArea / base[i]);
                }
            }
            return prevY;
        }

        private static int lowerBound(long[] arr, long key) {
            int start = 0, end = arr.length;
            while (start < end) {
                int mid = (end + start) >>> 1;
                if (arr[mid] < key) start = mid + 1;
                else end = mid;
            }
            return start;
        }
    }
}
