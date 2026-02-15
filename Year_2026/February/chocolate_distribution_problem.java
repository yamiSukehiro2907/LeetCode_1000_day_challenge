package February;

import java.util.ArrayList;
import java.util.Collections;

public class chocolate_distribution_problem {
    static void main() {

    }

    static class Solution {
        public int findMinDiff(ArrayList<Integer> arr, int m) {
            Collections.sort(arr);
            int minDiff = Integer.MAX_VALUE;
            for(int i = m - 1 ; i < arr.size() ; i++){
                int diff = arr.get(i) - arr.get(i - m + 1);
                if(diff < minDiff) minDiff = diff;
            }
            return minDiff;
        }
    }
}
