package April;

public class painting_the_fence {
    static void main() {

    }

    static class Solution {
        int countWays(int n, int k) {
            if(n == 1) return k;
            if(n == 2) return k * k;
            int same = k;
            int diff = k * k - k;
            for(int i = 3 ; i <= n ; i++){
                // we can only choose 1 color which is different from previous
                int newSame = diff;
                // for different we can choose any k - 1 color which is different from previous
                int newDiff = (same + diff) * (k - 1);
                same = newSame;
                diff = newDiff;
            }
            return same + diff;
        }
    }

}
