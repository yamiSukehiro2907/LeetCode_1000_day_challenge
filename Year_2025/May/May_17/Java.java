public class Java {
    public static void main(String[] args) {

    }

    class Solution {
        public void sortColors(int[] nums) {
            int start = 0, mid = 0, end = nums.length - 1;
            while (mid <= end) {
                if (nums[mid] == 0) {
                    swap(nums, mid, start);
                    start++;
                    mid++; // either the previous will be 0 or 1 so move mid too
                } else if (nums[mid] == 1) {
                    mid++;
                } else {
                    swap(nums, mid, end); // we are not sure about end's swapped value
                    end--;
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}