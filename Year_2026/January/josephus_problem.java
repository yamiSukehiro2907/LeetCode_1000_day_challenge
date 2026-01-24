package January;

import java.util.ArrayList;

public class josephus_problem {
    static void main() {

    }

    static class Solution {
        public int josephus(int n, int k) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) list.add(i);
            int index = 0;
            while (list.size() > 1) {
                index = (index + k - 1) % list.size();
                list.remove(index);
            }
            return list.getFirst();
        }
    }
}
