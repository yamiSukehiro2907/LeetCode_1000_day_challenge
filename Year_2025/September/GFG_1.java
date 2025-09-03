import java.util.*;

public class GFG_1 {
    public static void main(String[] args) {

    }

    static class Solution {
        static class Pair {
            int freq, value;

            Pair(int freq, int value) {
                this.freq = freq;
                this.value = value;
            }
        }

        public int sumOfModes(int[] arr, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>();
            PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> {
                if (a.freq == b.freq)
                    return Integer.compare(a.value, b.value);
                return Integer.compare(b.freq, a.freq);
            });

            int sum = 0;
            int n = arr.length;

            for (int i = 0; i < k; i++) {
                int val = arr[i];
                freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
                maxHeap.offer(new Pair(freqMap.get(val), val));
            }

            for (int i = k; i < n; i++) {
                while (freqMap.get(maxHeap.peek().value) != maxHeap.peek().freq) {
                    maxHeap.poll();
                }
                sum += maxHeap.peek().value;
                int outVal = arr[i - k];
                freqMap.put(outVal, freqMap.get(outVal) - 1);
                int inVal = arr[i];
                freqMap.put(inVal, freqMap.getOrDefault(inVal, 0) + 1);
                maxHeap.offer(new Pair(freqMap.get(inVal), inVal));
            }
            while (freqMap.get(maxHeap.peek().value) != maxHeap.peek().freq) {
                maxHeap.poll();
            }
            sum += maxHeap.peek().value;
            return sum;
        }
    }
}
// 1 2 3 2 5 2 4 4

// pq 1,1 -> 1,2 -> 1,3 -> 2,2
// map 1,0 -> 2,2 -> 3,1
// sum = 1
//