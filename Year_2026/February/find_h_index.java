package February;

import java.util.Arrays;

public class find_h_index {
    static void main() {

    }

    static class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int n = citations.length;
            for (int i = 0; i < n; i++) if (n - i <= citations[i]) return n - i;
            return 0;
        }

    }
}
