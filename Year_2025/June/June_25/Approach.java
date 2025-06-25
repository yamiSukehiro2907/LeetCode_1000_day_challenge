package June_25;

import java.util.*;

public class Approach {
    public static void main(String[] args) {

    }

    static class Solution {
        public long kthSmallestProduct(int[] a, int[] b, long k) {
            int p1 = findFirstZero(a);
            int p2 = findFirstZero(b);
            int[] pos1 = Arrays.copyOfRange(a, p1, a.length);
            int[] pos2 = Arrays.copyOfRange(b, p2, b.length);
            int[] neg1 = negCopy(a, p1);
            int[] neg2 = negCopy(b, p2);

            int negCnt = neg1.length * pos2.length + pos1.length * neg2.length;
            long sign = 1;
            if (k > negCnt) {
                k -= negCnt;
            } else {
                int[] tmp = neg2;
                neg2 = pos2;
                pos2 = tmp;
                sign = -1;
                k = negCnt - k + 1;
            }

            long lo = 0, hi = (long) Math.pow(10, 10);
            while (lo < hi) {
                long mid = lo + (hi - lo) / 2;
                if (count(neg1, neg2, mid) + count(pos1, pos2, mid) >= k) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return lo * sign;
        }

        long count(int[] x, int[] y, long max) {
            long cnt = 0;
            int j = y.length - 1;
            for (int i = 0; i < x.length; i++) {
                while (j >= 0 && (long) x[i] * y[j] > max) {
                    j--;
                }
                cnt += j + 1;
            }
            return cnt;
        }

        private int findFirstZero(int[] arr) {
            int l = 0, r = arr.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (arr[m] >= 0) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }

        private int[] negCopy(int[] arr, int len) {
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                res[i] = -arr[len - 1 - i];
            }
            return res;
        }
    }
}
