package March;

public class matrix_similarity_after_cyclic_shifts {
    static void main() {

    }

    static class Solution {
        public boolean areSimilar(int[][] mat, int k) {
            int m = mat.length;
            int n = mat[0].length;
            k %= n;
            if (k == 0) return true;
            for (int[] ints : mat) for (int j = 0; j < n; j++) if (ints[j] != ints[(j - k + n) % n]) return false;
            return true;
        }
    }
}
