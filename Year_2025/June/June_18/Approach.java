
public class Approach {
    public static void main(String[] args) {
        int arr[] = { 4, 2, 9, 8, 2, 12, 7, 12, 10, 5, 8, 5, 5, 7, 9, 2, 5, 11 };
        int k = 14;
        Solution solution = new Solution();
        int ans[][] = solution.divideArray(arr, k);
        for (int[] row : ans) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.print(",");
        }
    }

    static class Solution {
        public int[][] divideArray(int[] nums, int k) {
            if (nums.length == 0)
                return new int[][] {};
            int maxVal = Integer.MIN_VALUE;
            for (int num : nums)
                maxVal = Math.max(maxVal, num);
            int[] freq = new int[maxVal + 1];
            for (int num : nums)
                freq[num]++;
            int[][] ans = new int[nums.length / 3][3];
            int row = 0, col = 0;
            for (int i = 0; i < maxVal + 1; i++) {
                if (freq[i] > 0) {
                    while (freq[i]-- > 0) {
                        ans[row][col++] = i;
                        if (col == 3) {
                            if (Math.abs(ans[row][col - 1] - ans[row][col - 3]) > k)
                                return new int[][] {};
                            row++;
                            col = 0;
                        }
                    }
                }
            }
            return ans;
        }
    }
    /*
    class Solution {
    public int[][] divideArray(int[] nums, int diff) {
        Arrays.sort(nums);
        int n = nums.length;
        if(n % 3 != 0) return new int[][]{};
        if(n > 1 && nums[1] - nums[0] > diff) return new int[][]{};
        int ans[][] = new int[n/3][3];
        int j = 0;
        for(int i = 0 ; i < n ; i += 3){
            int[] temp = new int[3];
            temp[0] = nums[i];
            temp[1] = nums[i + 1];
            temp[2] = nums[i + 2];
            if(valid(temp , diff)){
                ans[j++] = temp;
            }else return new int[][]{};
        }
        return ans;
    }
    private boolean valid(int[] num ,int diff){
        return (num[2] - num[1] <= diff) && (num[2] - num[0] <= diff);
    }
}
     */
}
