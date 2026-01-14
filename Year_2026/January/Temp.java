package January;

import java.util.Arrays;

public class Temp {
    static void main() {

    }

    static class Solution {
        static final class Event {
            final long y;
            final int l, r;
            final int delta;

            Event(long y, int l, int r, int delta) {
                this.y = y;
                this.l = l;
                this.r = r;
                this.delta = delta;
            }
        }

        static final class SegTree {
            final long[] xs;
            final long[] cover;
            final int[] cnt;

            SegTree(long[] xs) {
                this.xs = xs;
                int n = Math.max(1, xs.length - 1);
                this.cover = new long[n << 2];
                this.cnt = new int[n << 2];
            }

            long covered() {
                return cover[1];
            }

            void update(int l, int r, int delta) {
                if (l >= r) return;
                update(1, 0, xs.length - 1, l, r, delta);
            }

            private void update(int node, int L, int R, int ql, int qr, int delta) {
                if (qr <= L || R <= ql) return;
                if (ql <= L && R <= qr) {
                    cnt[node] += delta;
                    pushUp(node, L, R);
                    return;
                }
                int mid = (L + R) >>> 1;
                update(node << 1, L, mid, ql, qr, delta);
                update(node << 1 | 1, mid, R, ql, qr, delta);
                pushUp(node, L, R);
            }

            private void pushUp(int node, int L, int R) {
                if (cnt[node] > 0) {
                    cover[node] = xs[R] - xs[L];
                } else if (L + 1 == R) {
                    cover[node] = 0;
                } else {
                    cover[node] = cover[node << 1] + cover[node << 1 | 1];
                }
            }
        }

        public double separateSquares(int[][] squares) {
            int n = squares.length;
            if (n == 0) return -1;

            long[] xs = new long[2 * n];
            int p = 0;
            for (int[] s : squares) {
                long x1 = s[0];
                long x2 = (long) s[0] + s[2];
                xs[p++] = x1;
                xs[p++] = x2;
            }
            Arrays.sort(xs);
            int m = 1;
            for (int i = 1; i < xs.length; i++) {
                if (xs[i] != xs[m - 1]) xs[m++] = xs[i];
            }
            xs = Arrays.copyOf(xs, m);
            if (xs.length < 2) {
                long minY = Long.MAX_VALUE;
                for (int[] s : squares) minY = Math.min(minY, (long) s[1]);
                return (double) minY;
            }

            Event[] events = new Event[2 * n];
            int e = 0;
            for (int[] s : squares) {
                long x1 = s[0];
                long x2 = (long) s[0] + s[2];
                long y1 = s[1];
                long y2 = (long) s[1] + s[2];
                int l = lowerBound(xs, x1);
                int r = lowerBound(xs, x2);
                if (l < r) {
                    events[e++] = new Event(y1, l, r, +1);
                    events[e++] = new Event(y2, l, r, -1);
                }
            }
            if (e == 0) return -1;
            events = Arrays.copyOf(events, e);
            Arrays.sort(events, (a, b) -> Long.compare(a.y, b.y));

            SegTree st = new SegTree(xs);

            long[] sY = new long[e];
            long[] eY = new long[e];
            long[] base = new long[e];
            int gi = 0;

            long area = 0;
            long prevY = events[0].y;
            long baseLen = 0;
            int i = 0;
            while (i < e) {
                long currY = events[i].y;
                long dy = currY - prevY;
                if (dy != 0 && baseLen != 0) {
                    area += baseLen * dy;
                    sY[gi] = prevY;
                    eY[gi] = currY;
                    base[gi] = baseLen;
                    gi++;
                }
                int j = i;
                while (j < e && events[j].y == currY) {
                    st.update(events[j].l, events[j].r, events[j].delta);
                    j++;
                }
                baseLen = st.covered();
                prevY = currY;
                i = j;
            }

            if (area == 0) return prevY;
            double target = area / 2.0;
            long pref = 0;
            for (int k = 0; k < gi; k++) {
                long a = base[k] * (eY[k] - sY[k]);
                if (pref + a < target) {
                    pref += a;
                } else {
                    double remain = target - pref;
                    return sY[k] + remain / base[k];
                }
            }
            return prevY;
        }

        private static int lowerBound(long[] a, long key) {
            int lo = 0, hi = a.length;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (a[mid] < key) lo = mid + 1;
                else hi = mid;
            }
            return lo;
        }
    }
}
