package Year_2025.May.May_10;

public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int arr1[] = { 20, 0, 18, 11, 0, 0, 0, 0, 0, 0, 17, 28, 0, 11, 10, 0, 0, 15, 29 };
        int arr2[] = { 16, 9, 25, 16, 1, 9, 20, 28, 8, 0, 1, 0, 1, 27 };
        System.out.println(solution.minSum(arr1, arr2));
    }

}

class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long sum1 = 0, sum2 = 0;
        int zero1 = 0, zero2 = 0;
        for (int num : nums1) {
            sum1 += num;
            if (num == 0)
                zero1++;
        }
        for (int num : nums2) {
            sum2 += num;
            if (num == 0)
                zero2++;
        }
        System.out.println("SUM1 : " + sum1 + " SUM2 : " + sum2 + " ZERO1 : " + zero1 + " ZERO2 : " + zero2);
        if (sum1 == sum2) {
            if (zero1 == 0 && zero2 == 0)
                return sum1;
            if (zero1 == 0 || zero2 == 0)
                return -1;
            return Math.max(zero1, zero2) + sum1;
        }
        if (zero1 == 0 && zero2 == 0)
            return -1;
        if (zero1 == 0) {
            if (sum1 <= sum2)
                return -1; // if the sum1 is less than sum2 we cannot increase it's total sum as it does
                           // not contains zero to be replaced
            if (sum2 + zero2 > sum1)
                return -1; // if filling all zeros by 1 increases sum2 from sum1 then not possible
        }
        if (zero2 == 0) {
            if (sum2 <= sum1)
                return -1;
            if (sum1 + zero1 > sum2)
                return -1;
        }
        return Math.max(sum1 + zero1, sum2 + zero2);
    }
}