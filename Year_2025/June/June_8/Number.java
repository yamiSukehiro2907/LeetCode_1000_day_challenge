package June_8;

import java.util.*;

public class Number {

    static class Solution {

        public List<Integer> lexicalOrder(int n) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                solve(list, i, n);
            }
            return list;
        }

        private void solve(List<Integer> list, int num, int n) {
            if (num > n) {
                return;
            }
            list.add(num);
            for (int i = 0; i < 10; i++) {
                int p = num * 10 + i;
                if (p > n) {
                    return;
                }
                solve(list, p, n);
            }
        }
    }

    public static void main(String[] args) {

    }
}
