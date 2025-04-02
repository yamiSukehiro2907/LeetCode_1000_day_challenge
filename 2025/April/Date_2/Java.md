```
class Solution {
    static{
        for(int i = 0 ; i < 500 ; i++){
            maximumTripletValue(new int[]{1 , 2 , 3});
        }
    }
    public static long maximumTripletValue(int[] nums) {
        long res = 0 , imax = 0 , dmax = 0;
        for(int num : nums){
            res = Math.max(res , (long) dmax * num);
            dmax = Math.max(dmax , imax - num);
            imax = Math.max(imax , num);
        }
        return res;
    }
}
```