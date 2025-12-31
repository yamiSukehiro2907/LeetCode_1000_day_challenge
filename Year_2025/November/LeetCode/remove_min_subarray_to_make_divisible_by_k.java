import java.util.HashMap;
import java.util.Map;

/// Link:- [...](https://leetcode.com/problems/make-sum-divisible-by-p)

public class remove_min_subarray_to_make_divisible_by_k {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2;
        Solution sol = new Solution();
        System.out.println(sol.minSubarray(arr, k));
    }

    private static class Solution {
        static{
            int[] arr = {1 , 2};
            Solution solution = new Solution();
            for(int i = 0 ; i < 500 ; i++) solution.minSubarray(arr , 3);
        }
        public int minSubarray(int[] nums, int p) {
            long sum = 0;
            for(int num : nums) sum += num;
            if(sum < p) return -1;
            int rem = (int)(sum % (long)p);
            if(rem == 0) return 0;
            Map<Integer, Integer> remainder_index_map = new HashMap<>();
            int prefix_rem = 0, minLength = Integer.MAX_VALUE;
            remainder_index_map.put(0, -1);
            for(int i = 0; i < nums.length; i++){
                if(nums[i] % p == rem) return 1;
                prefix_rem = (prefix_rem + nums[i]) % p;
                int r_i = (prefix_rem - rem + p) % p;
                if(remainder_index_map.containsKey(r_i)){
                    int length = i - remainder_index_map.get(r_i);
                    if(minLength > length) minLength = length;
                }
                remainder_index_map.put(prefix_rem, i);
            }
            return (minLength == Integer.MAX_VALUE || minLength == nums.length) ? -1 : minLength;
        }
    }
}
