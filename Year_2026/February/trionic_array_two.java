package February;

public class trionic_array_two {
    static void main() {

    }

    static class Solution {
        public long maxSumTrionic(int[] nums) {
            int n = nums.length;
            long maxTrionicSum = Long.MIN_VALUE;
            for (int startIdx = 0; startIdx < n; startIdx++) {
                int currentIdx = startIdx + 1;
                long currentSum = 0;
                while (currentIdx < n && nums[currentIdx - 1] < nums[currentIdx]) currentIdx++;
                int peakIdx = currentIdx - 1;
                if (peakIdx == startIdx) continue;
                currentSum += nums[peakIdx] + nums[peakIdx - 1];
                while (currentIdx < n && nums[currentIdx - 1] > nums[currentIdx]) {
                    currentSum += nums[currentIdx];
                    currentIdx++;
                }
                int valleyIdx = currentIdx - 1;
                boolean hasNoDescent = (valleyIdx == peakIdx);
                boolean valleyAtEnd = (valleyIdx == n - 1);
                boolean noProperSecondAscent = (currentIdx < n && nums[currentIdx] <= nums[valleyIdx]);
                if (hasNoDescent || valleyAtEnd || noProperSecondAscent) {
                    startIdx = valleyIdx;
                    continue;
                }
                currentSum += nums[valleyIdx + 1];
                long maxForwardExtension = 0;
                long forwardSum = 0;
                for (int k = valleyIdx + 2; k < n && nums[k] > nums[k - 1]; k++) {
                    forwardSum += nums[k];
                    maxForwardExtension = Math.max(maxForwardExtension, forwardSum);
                }
                currentSum += maxForwardExtension;
                long maxBackwardExtension = 0;
                long backwardSum = 0;
                for (int k = peakIdx - 2; k >= startIdx; k--) {
                    backwardSum += nums[k];
                    maxBackwardExtension = Math.max(maxBackwardExtension, backwardSum);
                }
                currentSum += maxBackwardExtension;
                maxTrionicSum = Math.max(maxTrionicSum, currentSum);
                startIdx = valleyIdx - 1;
            }
            return maxTrionicSum;
        }
    }
}
