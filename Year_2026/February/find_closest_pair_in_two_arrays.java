package February;

import java.util.ArrayList;

public class find_closest_pair_in_two_arrays {
    static void main() {

    }

    static class Solution {
        public static ArrayList<Integer> findClosestPair(int[] arr1, int[] arr2, int x) {
            int i = 0, j = arr2.length - 1;
            ArrayList<Integer> ans = null;
            while (i < arr1.length && j >= 0) {
                int sum = arr2[j] + arr1[i];
                if ((ans == null) || Math.abs(sum - x) < Math.abs(x - ans.get(0) - ans.get(1))) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(arr1[i]);
                    temp.add(arr2[j]);
                    ans = temp;
                }
                if (sum > x) j--;
                else i++;
            }
            return ans;
        }
    }
}
