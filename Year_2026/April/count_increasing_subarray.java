package April;

public class count_increasing_subarray {
    static void main() {
        int[] arr = {1, 4, 5, 3, 7, 9};
        Solution solution = new Solution();
        System.out.println(solution.countIncreasing(arr));
    }

    static class Solution {
        public int countIncreasing(int[] arr) {
            int ans = 0;
            int length = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[i - 1]) length++;
                else {
                    int sub = (length * (length + 1)) / 2 - length;
                    ans += sub;
                    length = 1;
                }
            }
            int sub = (length * (length + 1)) / 2 - length;
            ans += sub;
            return ans;
        }
    }

}
