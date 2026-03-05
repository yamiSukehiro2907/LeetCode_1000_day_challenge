package March;

public class longest_subarray_with_atmost_two_distinct_integers {
    static void main() {

    }

    static class Solution {
        public int totalElements(int[] arr) {
            int uniqueCount = 0;
            int maxElement = arr[0];
            for (int num : arr) maxElement = Math.max(maxElement, num);
            int[] freq = new int[maxElement + 1];
            int left = -1;
            int maxLength = 0;
            for (int right = 0; right < arr.length; right++) {
                if (freq[arr[right]] == 0) uniqueCount++;
                freq[arr[right]]++;
                while (uniqueCount > 2) {
                    if (freq[arr[++left]] == 1) uniqueCount--;
                    freq[arr[left]]--;
                }
                int length = right - left;
                if (length > maxLength) maxLength = length;
            }
            return maxLength;
        }
    }
}
