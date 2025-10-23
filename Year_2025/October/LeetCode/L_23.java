public class L_23 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public boolean hasSameDigits(String s) {
            char[] arr = s.toCharArray(), arr1 = s.toCharArray();
            int lastIndex = arr.length - 1;
            while (lastIndex >= 2) {
                int index = 0;
                while (index < lastIndex) arr1[index] = (char) (((arr[index] - '0') + (arr[++index] - '0')) % 10 + '0');
                lastIndex--;
                arr = arr1;
            }
            return arr[0] == arr[1];
        }
    } 
}
