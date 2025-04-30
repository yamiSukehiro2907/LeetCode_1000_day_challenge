class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for(int num : nums) if(num > max) max = num;
        int left = 0;
        int freq = 0;
        long ans = 0;
        for(int right = 0 ; right < n ; right++){
            if(nums[right] == max) freq++;
            while(freq >= k){
                ans += (n - right);
                if(nums[left] == max) freq--;
                left++;
            }
        }
        return ans;
    }
}

/*
class Solution {
    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < n ; i++){
            if(max == nums[i]) list.add(i);
            else if(max < nums[i]){
                list.clear();
                list.add(i);
                max = nums[i];
            }
        }
        int size = list.size();
        if(size < k) return 0;
        long total = 0;
        for(int index = k - 1 ; index < size ; index++){
            int left = list.get(index - k + 1) + 1;
            int right = ((index + 1 < size) ? list.get(index + 1) : n) - list.get(index) ;
            total += (long)((long)left * right);
        }
        return total;
    }
}
 */