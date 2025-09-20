import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class L_20 {
    private static class Router {
        private final int maxSize;
        private Queue<int[]> queue;
        private Map<Integer, List<int[]>> destinations;

        public Router(int memoryLimit) {
            this.maxSize = memoryLimit;
            this.queue = new LinkedList<>();
            this.destinations = new HashMap<>();
        }

        public boolean addPacket(int source, int destination, int timestamp) {
            if (!destinations.containsKey(destination))
                destinations.put(destination, new ArrayList<>());
            List<int[]> list = destinations.get(destination);
            int left = find(list, timestamp, true, 0);
            if (!list.isEmpty()) {
                for (int i = left; i < list.size() && list.get(i)[1] == timestamp; i++) {
                    if (list.get(i)[0] == source)
                        return false; // found same
                }
            }
            destinations.get(destination).add(new int[] { source, timestamp });
            queue.offer(new int[] { source, destination, timestamp });
            if (queue.size() > maxSize)
                forwardPacket();
            return true;
        }

        public int[] forwardPacket() {
            if (queue.isEmpty())
                return new int[0];
            int[] packet = queue.poll();
            destinations.get(packet[1]).remove(0);
            return packet;
        }

        public int getCount(int destination, int startTime, int endTime) {
            if (!destinations.containsKey(destination))
                return 0;
            List<int[]> list = destinations.get(destination);
            int left = find(list, startTime, true, 0);
            if (left >= list.size() || list.get(left)[1] > endTime)
                return 0;
            int right = find(list, endTime, false, left);
            if (right < 0 || list.get(right)[1] < startTime)
                return 0;
            return right - left + 1;
        }

        private int find(List<int[]> list, int target, boolean less, int start) {
            int end = list.size() - 1;
            int ans = 0;
            while (start <= end) {
                int mid = start + ((end - start) / 2);
                int timestamp = list.get(mid)[1];
                if (less) {
                    if (timestamp < target)
                        start = mid + 1;
                    else {
                        ans = mid;
                        end = mid - 1;
                    }
                } else {
                    if (timestamp > target)
                        end = mid - 1;
                    else {
                        ans = mid;
                        start = mid + 1;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {

    }
}
