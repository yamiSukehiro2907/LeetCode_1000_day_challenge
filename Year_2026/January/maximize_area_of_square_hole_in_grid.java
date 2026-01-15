package January;

import java.util.Arrays;

public class maximize_area_of_square_hole_in_grid {
    static void main() {

    }

    static class Solution {
        public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
            // when we remove any bar then it opens area in the whole grid
            //  so if we can find the max area we can open by removing bars in both direction
            // then the minWindow forming square in that area will be answer
            int maxSideHorizontal = find(hBars);
            int maxSideVertical = find(vBars);
            int side = Math.min(maxSideHorizontal, maxSideVertical);
            return side * side;
        }

        private int find(int[] bars) {
            if (bars.length == 0) return 1;
            // here it is guaranteed that at least one bar will be present here to be removed so above condition is not important
            // if we remove n bars then we get space of n + 2 bars to be filled with water
            Arrays.sort(bars);
            int maxWindow = 0, barsRemoved = 0;
            for (int i = 1; i < bars.length; i++) {
                if (bars[i] - bars[i - 1] == 1) barsRemoved++;
                else barsRemoved = 0;
                if (barsRemoved > maxWindow) maxWindow = barsRemoved;
            }
            return maxWindow + 2;
        }
    }
}
