package February;

public class longest_balanced_subarray_one {
   public static void main(String[] args){
     
   }
   static class Solution {
    private static final int[] freq = new int[100001];
    private int[] nums;
    public int longestBalanced(int[] nums) {
        this.nums = nums;
        int maxLength = nums.length;
        while(maxLength > 0){
            clear();
            if(possible(maxLength)) return maxLength;
            maxLength--;
        }
        return 0;
    }
    private boolean possible(int k){
        int oddCount = 0 , evenCount = 0;
        for(int i = 0 ; i < k ; i++){
            if(nums[i] % 2 == 0 && freq[nums[i]] == 0) evenCount++; 
            if(nums[i] % 2 == 1 && freq[nums[i]] == 0) oddCount++; 
            freq[nums[i]]++;
        }
        if(oddCount == evenCount) return true;
        for(int i = k ; i < nums.length ; i++){
            if(nums[i] % 2 == 0 && freq[nums[i]] == 0) evenCount++; 
            if(nums[i] % 2 == 1 && freq[nums[i]] == 0) oddCount++; 
            freq[nums[i]]++;
            freq[nums[i - k]]--;
            if(nums[i - k] % 2 == 0 && freq[nums[i - k]] == 0) evenCount--; 
            if(nums[i - k] % 2 == 1 && freq[nums[i - k]] == 0) oddCount--; 
            if(evenCount == oddCount) return true;
        }
        return false;
    }

    private void clear(){
        for(int num : nums) freq[num] = 0; 
    }
}
}
