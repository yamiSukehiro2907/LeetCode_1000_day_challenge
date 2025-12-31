import java.util.*;

public class July_7 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int maxEvents(int[][] events) {
            Arrays.sort(events, (a, b) -> a[1] - b[1]);
            int[] root = new int[events[events.length - 1][1] + 2];
            for (int i = 0; i < root.length; i++)
                root[i] = i;
            int res = 0;
            for (int[] e : events) {
                int availableSlot = find(root, e[0]);
                if (availableSlot <= e[1]) {
                    res++;
                    root[availableSlot] = find(root, availableSlot + 1);
                }

            }
            return res;
        }

        public int find(int[] root, int i) {
            if (root[i] != i)
                return root[i] = find(root, root[i]);
            return i;
        }
    }
}