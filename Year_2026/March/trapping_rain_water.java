package March;

public class trapping_rain_water {
    static void main() {

    }

    static class Solution {
        public int maxWater(int[] arr) {
            int ans = 0;
            int left = 0;
            int n = arr.length;
            int right = n - 1;
            int leftmax = 0;
            int rightmax = 0;
            while (left < right) {
                if (arr[left] < arr[right]) {
                    if (arr[left] > leftmax) leftmax = arr[left];
                    else ans += leftmax - arr[left];
                    left++;
                } else {
                    if (arr[right] > rightmax) rightmax = arr[right];
                    else ans += rightmax - arr[right];
                    right--;
                }
            }
            return ans;
        }
    }
}
