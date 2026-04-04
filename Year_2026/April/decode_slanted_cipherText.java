package April;

public class decode_slanted_cipherText {
    static void main() {

    }

    static class Solution {
        public String decodeCiphertext(String encodedText, int rows) {
            if (rows == 1) return encodedText;
            char[] str = encodedText.toCharArray();
            int cols = str.length / rows;
            char[] ans = new char[str.length];
            int k = 0;
            for (int col = 0; col < cols; col++) {
                int i = 0, j = col;
                while (i < rows && j < cols) ans[k++] = str[(i++) * cols + (j++)];
            }
            while (k > 0 && ans[k - 1] == ' ') k--;
            return new String(ans, 0, k);
        }
    }
}
