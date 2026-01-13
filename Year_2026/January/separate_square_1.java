package January;

public class separate_square_1 {
    static void main() {

    }

    static class Solution {
        public double separateSquares(int[][] squares) {
            double end = 0;
            for (int[] sq : squares) if (end < sq[1] + sq[2]) end = sq[1] + sq[2];
            double start = 0;
            while (end - start > 1e-5) {
                double mid = ((end - start) / 2 + start);
                if (possible(mid, squares)) end = mid;
                else start = mid;
            }
            return start;
        }

        private boolean possible(double line, int[][] squares) {
            double below = 0, above = 0;
            for (int[] square : squares) {
                double y = square[1], length = square[2];
                if (y >= line) above += length * length;
                else if (y + length <= line) below += length * length;
                else {
                    above += (y + length - line) * length;
                    below += (line - y) * length;
                }
            }
            return below >= above;
        }
    }
}
