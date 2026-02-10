package February;

public class koko_eating_bananas{
   public static void main(String[] args){
      
   }
   static class Solution {
    public int kokoEat(int[] arr, int k) {
        long totalBanana = 0L;
        for(int num : arr) totalBanana += num;
        long start = 1L , end = totalBanana;
        long ans = 0L;
        while(start <= end){
            long mid = start + ((end - start) >> 1L);
            if(possible(mid , arr , k)){
                ans = mid;
                end = mid - 1;
            }else start = mid + 1;
        }
        return (int)ans;
    }
    private boolean possible(long rate , int[] nums , int hour){
        for(int num : nums){
            int hourConsumed = num / (int)rate;
            if(num % rate != 0) hourConsumed++;
            hour -= hourConsumed;
            if(hour < 0) return false;
        }
        return hour >= 0;
    }
}

}
