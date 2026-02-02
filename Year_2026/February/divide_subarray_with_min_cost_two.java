package February;

import java.util.TreeMap;

public class divide_subarray_with_min_cost_two {

    static void main() {
        int[] arr = {10, 8, 18, 9};
        int k = 3;
        int dist = 1;
        Solution s = new Solution();
        System.out.println(s.minimumCost(arr, k, dist));
    }

    static class Solution {
        private TreeMap<Integer, Integer> selected, candidates;
        private long selectedSum;
        private int selectedCount, targetSize;

        public long minimumCost(int[] nums, int k, int dist) {
            this.selected = new TreeMap<>();
            this.candidates = new TreeMap<>();
            this.selectedSum = 0L;
            this.selectedCount = 0;
            this.targetSize = k - 2;
            for (int i = 2; i <= 1 + dist; i++) addToWindow(nums[i]);
            long minSum = Long.MAX_VALUE;
            for (int currIndex = 1; currIndex + k - 2 < nums.length; currIndex++) {
                long totalCost = nums[0] + nums[currIndex] + selectedSum;
                if (minSum > totalCost) minSum = totalCost;
                removeFromWindow(nums[currIndex + 1]);
                if (currIndex + dist + 1 < nums.length) addToWindow(nums[currIndex + dist + 1]);
            }
            return minSum;
        }

        private void removeFromWindow(int num) {
            if (selected.containsKey(num)) {
                removeFromSelected(num);
                if (!candidates.isEmpty() && selectedCount < targetSize) {
                    int element = candidates.firstKey();
                    removeFromCandidates(element);
                    addToSelected(element);
                }
            } else if (candidates.containsKey(num)) removeFromCandidates(num);
        }

        private void addToWindow(int num) {
            if (selectedCount < targetSize) addToSelected(num);
            else if (!selected.isEmpty() && num < selected.lastKey()) {
                int maxSelected = selected.lastKey();
                removeFromSelected(maxSelected);
                addToSelected(num);
                addToCandidates(maxSelected);
            } else addToCandidates(num);
        }

        private void addToSelected(int num) {
            selected.put(num, selected.getOrDefault(num, 0) + 1);
            selectedSum += num;
            selectedCount++;
        }

        private void removeFromSelected(int num) {
            int count = selected.get(num);
            if (count == 1) selected.remove(num);
            else selected.put(num, count - 1);
            selectedSum -= num;
            selectedCount--;
        }

        private void removeFromCandidates(int num) {
            int count = candidates.get(num);
            if (count == 1) candidates.remove(num);
            else candidates.put(num, count - 1);
        }

        private void addToCandidates(int num) {
            candidates.put(num, candidates.getOrDefault(num, 0) + 1);
        }
    }
}
