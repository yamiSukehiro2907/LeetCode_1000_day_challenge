package April;

import java.util.ArrayList;

public class sorted_subsequence_of_size_3 {
    static void main() {

    }

    static class Solution {
        public ArrayList<Integer> find3Numbers(int[] arr) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(Integer.MAX_VALUE);
            list.add(Integer.MAX_VALUE);
            list.add(Integer.MAX_VALUE);
            for (int num : arr) {
                if (num <= list.get(2)) list.set(2, num);
                else if (num <= list.get(1)) {
                    list.set(1, num);
                    list.set(0, list.get(2));
                } else {
                    list.set(2, num);
                    return list;
                }
            }
            // the last element stores the first element of possible new subsequence
            // while the first two elements store the
            return new ArrayList<>();
        }
    }
}
