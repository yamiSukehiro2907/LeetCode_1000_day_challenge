package Year_2025.April.Date_28;

class Solution {
    public long countSubarrays(int[] nums, long k) {
        long total = 0;
        int left = 0;
        int n = nums.length;
        long currsum = 0;
        int right = 0;
        for (right = 0; right < n; right++) {
            currsum += nums[right];
            while (left < right && getScore(left, right, currsum) >= k) {
                System.out.println("Found greater than k subarray");
                System.out.println("left index : " + left + " right index : " + right + " currSum : " + currsum
                        + " score : " + getScore(left, right, currsum));
                int length = Math.max(0, right - left);
                total += ((length) * (length + 1) / 2);
                currsum -= nums[left];
                left++;
                System.out.println("Total : " + total);
            }
            System.out.println("Outside the loop");
            System.out.println("left index : " + left + " right index : " + right + " currSum : " + currsum
                    + " score : " + getScore(left, right, currsum));
        }
        return total;
    }

    private long getScore(int left, int right, long currsum) {
        return ((long) (right + 1 - left) * (currsum));
    }
}

public class Java {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = { 1, 1, 1 };
        System.out.println(solution.countSubarrays(arr, (long) (5)));
    }
}