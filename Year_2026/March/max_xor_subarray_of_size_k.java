package March;

public class max_xor_subarray_of_size_k {
    static void main() {

    }

    static class Solution {
        public int maxSubarrayXOR(int[] arr, int k) {
            int totalXor = 0;
            int[] xors = new int[arr.length];
            int maxXor = 0;
            for (int i = 0; i < k; i++) {
                totalXor ^= arr[i];
                xors[i] = totalXor;
            }
            maxXor = totalXor;
            for (int i = k; i < arr.length; i++) {
                totalXor ^= arr[i];
                int subarrayXor = totalXor ^ xors[i - k];
                maxXor = Math.max(maxXor, subarrayXor);
                xors[i] = totalXor;
            }
            return maxXor;
        }
    }
}
