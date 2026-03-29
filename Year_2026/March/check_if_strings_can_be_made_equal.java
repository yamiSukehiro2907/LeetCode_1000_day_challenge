package March;

public class check_if_strings_can_be_made_equal {
    static void main() {

    }

    static class Solution {
        public boolean canBeEqual(String s1, String s2) {
            char[] arr1 = s1.toCharArray();
            char[] arr2 = s2.toCharArray();
            if (isEqual(arr1, arr2)) return true;
            swap(arr1, 0, 2);
            if (isEqual(arr1, arr2)) return true;
            swap(arr1, 1, 3);
            if (isEqual(arr1, arr2)) return true;
            swap(arr1, 0, 2);
            return isEqual(arr1, arr2);
        }

        private void swap(char[] arr, int i, int j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private boolean isEqual(char[] arr1, char[] arr2) {
            for (int i = 0; i < 4; i++) if (arr1[i] != arr2[i]) return false;
            return true;
        }
    }
}
