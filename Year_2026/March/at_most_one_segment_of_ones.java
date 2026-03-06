package March;

public class at_most_one_segment_of_ones {
    static void main() {

    }

    static class Solution {
        public boolean checkOnesSegment(String s) {
            char[] arr = s.toCharArray();
            int index = 0;
            while (index < arr.length && arr[index] == '1') index++;
            if (index == arr.length) return true;
            while (index < arr.length && arr[index] == '0') index++;
            return index == arr.length;
        }
    }
}
