public class Approach {
    public static void main(String[] args) {
        int arr[] = { 3, 6, 1, 2, 5 }; // 1 2 3 5 6
        int k = 2;
        System.out.println((new Solution()).partitionArray(arr, k));
    }

    static class Solution {
        public int partitionArray(int[] nums, int k) {
            int maxVal = Integer.MIN_VALUE;
            int minVal = Integer.MAX_VALUE;
            for (int num : nums) {
                if (num > maxVal)
                    maxVal = num;
                if (num < minVal)
                    minVal = num;
            }
            if (maxVal - minVal <= k)
                return 1;
            int freq[] = new int[maxVal - minVal + 1];
            for (int num : nums)
                freq[num - minVal]++;
            int currSub = 1;
            maxVal = maxVal - minVal;
            int x = k + 1;
            while (x <= maxVal) {
                while (x <= maxVal && freq[x] == 0)
                    x++;
                if (x <= maxVal) {
                    currSub++;
                    x += k + 1;
                }
            }
            return currSub;
        }
    }
    /*
    class Solution {
    public int partitionArray(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int num : nums){
            if(num > maxVal) maxVal = num;
            if(num < minVal) minVal = num;
        }
        if(maxVal - minVal <= k) return 1;
        int freq[] = new int[maxVal - minVal + 1];
        for (int num : nums) freq[num - minVal]++;
        int currSub = 1;
        Integer leftMostSubElement = null;
        for (int i = minVal ; i <= maxVal ; i++) {
            if (freq[i - minVal] > 0) {
                while (freq[i - minVal]-- > 0) {
                    if (leftMostSubElement == null) leftMostSubElement = i;
                    else {
                        int diff = i - leftMostSubElement;
                        if (diff > k) {
                            currSub++;
                            leftMostSubElement = i;
                        }
                    }
                }
            }
        }
        return currSub;
    }
}
     */
}
