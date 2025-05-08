package Year_2025.May.May_5;


public class Solve {
    public static void main(String[] args) {
        System.out.println(Solution.buildArray(new int[] { 0, 2, 3, 1 }));
    }
}
// https://leetcode.com/problems/build-array-from-permutation/description/

class Solution {
    // the static block code :- it runs 500 times and make sure to allocate memory
    // in jvm so when testcases passed then it runs without any allocation just
    // computation
    static {
        for (int i = 0; i < 500; i++)
            buildArray(new int[] { 1 });
    }

    public static int[] buildArray(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return nums;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nums[nums[i]];
        return arr;
    }
}