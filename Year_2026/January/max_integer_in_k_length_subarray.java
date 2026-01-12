package January;

import java.util.*;

public class max_integer_in_k_length_subarray {
    static void main() {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        Solution2 sol = new Solution2();
        System.out.println(sol.maxOfSubarrays(arr, k));
    }

    static class Solution2 {
        public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
            ArrayList<Integer> ans = new ArrayList<>();
            Deque<Integer> queue = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) queue.pollLast();
                queue.offerLast(i);
            }
            for (int i = k; i < arr.length; i++) {
                ans.add(arr[queue.peekFirst()]);
                while (!queue.isEmpty() && queue.peekFirst() <= i - k) queue.pollFirst();
                while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) queue.pollLast();
                queue.offerLast(i);
            }
            ans.add(arr[queue.peekFirst()]);
            return ans;
        }
    }

    static class Solution {
        public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
            SortedMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);
            for (int i = 0; i < k; i++) addNum(arr[i], map);
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(map.firstKey());
            for (int i = k; i < arr.length; i++) {
                deleteNum(arr[i - k], map);
                addNum(arr[i], map);
                ans.add(map.firstKey());
            }
            return ans;
        }

        private void addNum(int num, SortedMap<Integer, Integer> map) {
            int value = 1;
            if (map.containsKey(num)) {
                value += map.get(num);
                map.remove(num);
            }
            map.put(num, value);
        }

        private void deleteNum(int num, SortedMap<Integer, Integer> map) {
            int value = map.get(num);
            map.remove(num);
            if (value > 1) map.put(num, value - 1);
        }
    }
}