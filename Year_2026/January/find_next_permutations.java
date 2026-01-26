package January;

import java.util.ArrayList;

public class find_next_permutations {
    static void main() {

    }

    static class Solution {
        private static ArrayList<ArrayList<Integer>> ans;

        public static ArrayList<ArrayList<Integer>> permuteDist(int[] arr) {
            ans = new ArrayList<>();
            boolean[] taken = new boolean[arr.length];
            generate(arr, taken, new ArrayList<>());
            return ans;
        }

        private static void generate(int[] arr, boolean[] taken, ArrayList<Integer> list) {
            if (list.size() == arr.length) {
                ans.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                if (!taken[i]) {
                    list.add(arr[i]);
                    taken[i] = true;
                    generate(arr, taken, list);
                    taken[i] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }

    ;

}
