import java.util.*;

public class GFG_13 {
    public static void main(String[] args) {
        int[] X = { 1, 1, 2, 3, 4 };
        int[] Y = { 1, 2, 4 };
    }

    private static class Solution {
        public static int minCost(int n, int m, int[] X, int[] Y) {
            Arrays.sort(X);
            Arrays.sort(Y);
            int vp = 1;
            int hp = 1;
            int cost = 0;
            int v = X.length - 1;
            int h = Y.length - 1;
            while (v >= 0 && h >= 0) {
                if (X[v] > Y[h]) {
                    cost = cost + (X[v] * hp);
                    vp++;
                    v--;
                } else {
                    cost = cost + (Y[h] * vp);
                    hp++;
                    h--;
                }
            }
            while (v >= 0) {
                cost = cost + (X[v] * hp);
                vp++;
                v--;
            }
            while (h >= 0) {
                cost = cost + (Y[h] * vp);
                hp++;
                h--;
            }
            return cost;
        }
    }
}