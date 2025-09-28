import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GFG_29 {
    public static void main(String[] args) {
        
    }

    private static class Solution {

        public ArrayList<Integer> longestSubarray(int[] arr, int x) {
            PriorityQueue<Pair> minQueue = new PriorityQueue<>((a, b) -> {
                if (a.value == b.value)
                    return a.index - b.index;
                return a.value - b.value;
            });
            PriorityQueue<Pair> maxQueue = new PriorityQueue<>((a, b) -> {
                if (a.value == b.value)
                    return a.index - b.index;
                return b.value - a.value;
            });
            Queue<Integer> queue = new LinkedList<>();
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                while (!minQueue.isEmpty() && !maxQueue.isEmpty() && !queue.isEmpty()) {
                    Pair minPair = minQueue.peek();
                    if (minPair.index < queue.peek()) {
                        minQueue.poll();
                        continue;
                    }
                    Pair maxPair = maxQueue.peek();
                    if (maxPair.index < queue.peek()) {
                        maxQueue.poll();
                        continue;
                    }
                    if (Math.abs(maxPair.value - arr[i]) <= x && Math.abs(minPair.value - arr[i]) <= x) {
                        break;
                    } else {
                        temp.remove(0);
                        queue.poll();
                    }
                }
                minQueue.add(new Pair(i, arr[i]));
                maxQueue.add(new Pair(i, arr[i]));
                queue.offer(i);
                temp.add(arr[i]);
                if (temp.size() > list.size()) {
                    list.clear();
                    list = new ArrayList<>(temp);
                }
            }
            return list;
        }

        private class Pair {
            int index;
            int value;

            public Pair(int index, int value) {
                this.index = index;
                this.value = value;
            }
        }
    }
}
