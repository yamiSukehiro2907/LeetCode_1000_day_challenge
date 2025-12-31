package December.GFG;

public class kth_element_two_sorted_array {
    static void main() {

    }

    static class Solution {
        public int kthElement(int[] a, int[] b, int k) {
            int findInA = getAns(a, b, k, 0);
            if (findInA != -1) return findInA;
            return getAns(b, a, k, 0);
        }

        private int getAns(int[] a, int[] b, int k, int index) {
            while (index < a.length) {
                int num = a[index];
                int numberLessThanNum = find(b, num - 1) + index;
                index++;
                while (index < a.length && a[index] == num) index++;
                int totalNumLessThanEqualToNum = index + find(b, num);
                if (k > numberLessThanNum && k <= totalNumLessThanEqualToNum) return num;
            }
            return -1;
        }

        private int find(int[] arr, int target) {
            int start = 0, end = arr.length - 1, index = -1;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);
                if (arr[mid] > target) end = mid - 1;
                else {
                    index = mid;
                    start = mid + 1;
                }
            }
            return index + 1;
        }
    }
}
