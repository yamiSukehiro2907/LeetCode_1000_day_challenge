import java.util.*;

public class August_1 {
    public static void main(String[] args) {

    }

    @SuppressWarnings("unused")
    static class Solution {
        public void fun(List<List<Integer>> list, int i, int n) {
            List<Integer> li = new ArrayList<>();
            if (i >= n) {
                return;
            }
            if (i == 0) {
                li.add(1);
            } else {
                li.add(1);
                for (int j = 1; j < i; j++) {
                    li.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
                li.add(1);
            }
            list.add(li);
            fun(list, i + 1, n);
        }

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> list = new ArrayList<>();
            fun(list, 0, numRows);
            return list;
        }
    }
}