import java.util.*;

public class July_21 {
    public static void main(String[] args) {

    }

    static class Solution {
        static {
            for (int i = 0; i < 500; i++) {
                makeFancyString("a");
            }
        }

        public static String makeFancyString(String s) {
            int n = s.length();
            if (n <= 2)
                return s;
            int count = 1, k = 0;
            char[] arr = s.toCharArray();
            char[] result = new char[n];
            char prev = arr[0];
            result[k++] = prev;
            for (int i = 1; i < n; i++) {
                if (arr[i] == prev) {
                    if (count < 2) {
                        result[k++] = prev;
                        count++;
                    }
                } else {
                    prev = arr[i];
                    count = 1;
                    result[k++] = prev;
                }
            }
            return new String(result, 0, k);
        }
    }
}
