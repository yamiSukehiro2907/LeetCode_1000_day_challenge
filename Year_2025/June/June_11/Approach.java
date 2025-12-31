import java.util.*;

public class Approach {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Max Difference: " + solution.maxDifference("2222130", 2));
    }

    static class Solution {
        private static final int BAD = Integer.MAX_VALUE / 2;

        public int maxDifference(String s, int k) {
            char[] c = s.toCharArray();
            int n = c.length;
            int[] minAB = new int[4];
            int[] minBA = new int[4];
            boolean[] skip = new boolean[5];
            int res = Integer.MIN_VALUE;
            outer: for (char a = '1'; a <= '4'; a++) {
                inner: for (char b = '0'; b < a; b++) {
                    if (skip[b - '0'])
                        continue;
                    int r = 0, ar = 0, br = 0;
                    while (r < k || ar + br < 3 || ar == 0 || br == 0) {
                        if (r == n) {
                            if (br == 0)
                                skip[b - '0'] = true;
                            if (ar == 0) {
                                skip[a - '0'] = true;
                                continue outer;
                            } else
                                continue inner;
                        }
                        char ch = c[r++];
                        if (ch == a)
                            ar++;
                        else if (ch == b)
                            br++;
                    }
                    int l = 0, al = 0, bl = 0;
                    Arrays.fill(minAB, BAD);
                    Arrays.fill(minBA, BAD);
                    minAB[0] = minBA[0] = 0;
                    while (true) {
                        while (l < r - k) {
                            char ch = c[l++];
                            if (ch == a) {
                                if (ar == al + 1) {
                                    l--;
                                    break;
                                }
                                al++;
                            } else if (ch == b) {
                                if (br == bl + 1) {
                                    l--;
                                    break;
                                }
                                bl++;
                            } else
                                continue;
                            int p = ((al & 1) << 1) + (bl & 1);
                            int d = al - bl;
                            minAB[p] = Math.min(minAB[p], d);
                            minBA[p] = Math.min(minBA[p], -d);
                        }
                        int p = ((ar & 1) << 1) + (br & 1);
                        int d = ar - br;
                        res = Math.max(res, d - minAB[p ^ 2]);
                        res = Math.max(res, -d - minBA[p ^ 1]);
                        if (r == n)
                            break;
                        char ch = c[r++];
                        if (ch == a)
                            ar++;
                        else if (ch == b)
                            br++;
                        else {
                            while (r < n && (ch = c[r]) != a && ch != b)
                                r++;
                        }
                    }
                }
            }
            return res;
        }
    }
}