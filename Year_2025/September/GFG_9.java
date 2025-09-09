
import java.util.Arrays;

public class GFG_9 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int assignHole(int[] mices, int[] holes) {
            Arrays.sort(holes);
            Arrays.sort(mices);
            int maxDist = 0;
            for (int i = 0; i < mices.length; i++) {
                int dist = Math.abs(mices[i] - holes[i]);
                if (dist > maxDist) {
                    maxDist = dist;
                }
            }
            return maxDist;
        }
    }
}