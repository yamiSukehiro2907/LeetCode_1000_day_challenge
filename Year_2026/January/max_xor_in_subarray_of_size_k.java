package January;

public class max_xor_in_subarray_of_size_k {
    static void main() {

    }

    static class Solution {
        public int maxSubarrayXOR(int[] arr, int k) {
            int currentXor = 0;
            for(int i = 0 ; i < k ; i++) currentXor ^= arr[i];
            int maxXor = currentXor;
            for(int i = k ; i < arr.length ; i++){
                currentXor ^= arr[i];
                currentXor ^= arr[i - k];
                if(currentXor > maxXor) maxXor = currentXor;
            }
            return maxXor;
        }
    }

}
