package January;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class separate_square_2 {
    static void main() {

    }

    static class Solution {

        private static final class Event {
            final long yLine;
            final long left, right;
            final int delta;

            public Event(long yLine, long left, long right, int delta) {
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
            // 2 -> 4
            // 3 -> 8
            // 4 -> 12
            // 5 -> 16

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
                    count[node]++;
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
                long left = lowerBound(xs, xStart);
                long right = lowerBound(xs, xEnd);
                if (left < right) {
                    events[index++] = new Event(yStart, left, right, 1);
                    events[index++] = new Event(yEnd, left, right, -1);
                }
            }

            if (index == 0) return -1;
            events = Arrays.copyOf(events, index);
            Arrays.sort(events, (a, b) -> Long.compare(a.yLine, b.yLine));

            SegmentTree segmentTree = new SegmentTree(xs);

            long[] yStart = new long[index];
            long[] yEnd = new long[index];
            long[] base = new long[index];
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

    static class Solution2 {
        public double separateSquares(int[][] squares) {
            List<Event> sweepEvents = new ArrayList<>();
            for (int[] square : squares) {
                int x = square[0], y = square[1], side = square[2];
                sweepEvents.add(new Event(y, 1, x, x + side)); // 1 is for start of the square
                sweepEvents.add(new Event(y + side, -1, x, x + side)); // -1 is for end of the square
            }

            Collections.sort(sweepEvents); // it sorts the sweepEvents on the yLines

            List<Interval> activeIntervals = new ArrayList<>();
            List<double[]> processedStrips = new ArrayList<>();

            double totalArea = 0;
            int prevYLine = sweepEvents.get(0).yLine;

            for (Event event : sweepEvents) {
                if (event.yLine > prevYLine) {
                    double unionWidth = getUnionWidth(activeIntervals);
                    double height = (double) event.yLine - prevYLine;

                    if (unionWidth > 0) {
                        processedStrips.add(new double[]{prevYLine, height, unionWidth});
                        totalArea += unionWidth * height;
                    }
                }

                Interval currentInterval = new Interval(event.xStart, event.xEnd);
                if (event.type == 1) {
                    activeIntervals.add(currentInterval);
                } else {
                    activeIntervals.remove(currentInterval);
                }

                prevYLine = event.yLine;
            }

            double targetArea = totalArea / 2.0;
            double area = 0;

            for (double[] strip : processedStrips) {
                double bottomY = strip[0];
                double height = strip[1];
                double width = strip[2];

                double stripeArea = height * width;

                if (area + stripeArea >= targetArea) {
                    double missingArea = targetArea - area;
                    return bottomY + (missingArea / width);
                }
                area += stripeArea;
            }

            return 0.0;
        }

        private double getUnionWidth(List<Interval> activeIntervals) {
            if (activeIntervals.isEmpty()) return 0;

            List<Interval> sorted = new ArrayList<>(activeIntervals);
            Collections.sort(sorted);

            double unionLength = 0;
            double currentEnd = -1e18;

            for (Interval interval : sorted) {
                if (interval.start >= currentEnd) {
                    unionLength += (interval.end - interval.start);
                    currentEnd = interval.end;
                } else if (interval.end > currentEnd) {
                    unionLength += (interval.end - currentEnd);
                    currentEnd = interval.end;
                }
            }
            return unionLength;
        }

        private static class Event implements Comparable<Event> {
            private final int yLine;
            private final int type;
            private final int xStart;
            private final int xEnd;

            public Event(int yLine, int type, int xStart, int xEnd) {
                this.yLine = yLine;
                this.type = type;
                this.xStart = xStart;
                this.xEnd = xEnd;
            }

            public int compareTo(Event other) {
                return Integer.compare(this.yLine, other.yLine);
            }
        }

        private static class Interval implements Comparable<Interval> {

            private final int start;
            private final int end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

            public int compareTo(Interval other) {
                if (this.start != other.start) return Integer.compare(this.start, other.start);
                return Integer.compare(this.end, other.end);
            }

            public boolean equals(Object other) {
                if (this == other) return true;
                if (other == null || this.getClass() != other.getClass()) return false;
                Interval interval = (Interval) other;
                return this.start == interval.start && this.end == interval.end;
            }
        }
    }
}
