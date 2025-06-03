import java.util.*;

public class Approach {
    static class Solution {
        public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
            Set<Integer> keysInSet = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            int totalCandies = 0;
            for (int num : initialBoxes) {
                queue.offer(num);
            } // adding boxes person is holding
              // add key of that box if it exists
            while (!queue.isEmpty()) {
                System.out.print("Queue before processing: ");
                BoxesInQueue(queue);
                System.out.println();
                System.out.print("Keys in Set before processing: ");
                KeysInSet(keysInSet);
                System.out.println();
                int currBox = queue.poll(); // take out the box
                if (status[currBox] != 1 && keysInSet.contains(currBox)) {
                    status[currBox] = 1; // change the status of that box to open i.e 1
                }
                if (status[currBox] == 1) {
                    int[] boxes = containedBoxes[currBox];
                    for (int bx : boxes) {
                        queue.add(bx);
                    }
                    int[] KeysinBox = keys[currBox];
                    for (int ky : KeysinBox) {
                        keysInSet.add(ky);
                    }
                    totalCandies += candies[currBox];
                } else {
                    queue.add(currBox);
                }
                System.out.print("Queue after processing: ");
                BoxesInQueue(queue);
                System.out.println();
                System.out.print("Keys in Set after processing: ");
                KeysInSet(keysInSet);
                System.out.println();
                if (allQueueBoxClosed(queue, status, keysInSet)) {
                    return totalCandies;
                }
            }
            return totalCandies;
        }

        private boolean allQueueBoxClosed(Queue<Integer> queue, int[] status, Set<Integer> set) {
            Iterator<Integer> queueIterator = queue.iterator();
            while (queueIterator.hasNext()) {
                Integer box = queueIterator.next();
                if (status[box] == 1 || (status[box] == 0 && set.contains(box))) {
                    return false;
                }
            }
            return true;
        }

        private void BoxesInQueue(Queue<Integer> queue) {
            Iterator<Integer> queueIterator = queue.iterator();
            while (queueIterator.hasNext()) {
                System.out.print(queueIterator.next() + " ");
            }
        }

        private void KeysInSet(Set<Integer> set) {
            for (int num : set) {
                System.out.print(num + " ");
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] status = { 1, 0, 1, 0 };
        int[] candies = { 7, 5, 4, 100 };
        int[][] keys = { {}, {}, { 1 }, {} };
        int[][] containedBoxes = { { 1, 2 }, { 3 }, {}, {} };
        int[] initialBoxes = { 0 };
        System.out.println(solution.maxCandies(status, candies, keys, containedBoxes, initialBoxes));
    }
}