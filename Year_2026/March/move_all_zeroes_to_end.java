package March;

public class move_all_zeroes_to_end {
    static void main() {

    }

    static class Solution {
        void pushZerosToEnd(int[] arr) {
            int index = 0;
            for (int i = 0; i < arr.length; i++) if (arr[i] != 0) arr[index++] = arr[i];
            for (int i = index; i < arr.length; i++) arr[i] = 0;
        }
    }
}
