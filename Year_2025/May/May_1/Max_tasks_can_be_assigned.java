
package Year_2025.May.May_1;
import java.util.*;


public class Max_tasks_can_be_assigned {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] workers = {10, 15, 30};
        int[] tasks = {0, 10, 10, 10, 10};
        int pills = 3;
        int strength = 10;
        int tasks_assigned = solution.maxTaskAssign(tasks, workers, pills, strength);
        System.out.println(tasks_assigned);
    }

    static class Solution {
        public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
            Arrays.sort(tasks);
            Arrays.sort(workers);
            int end = Math.min(tasks.length, workers.length), start = 0, n = workers.length;
            while (start <= end) {
                int mid = start + end >> 1;
                if (possible(tasks, workers, pills, strength, mid, n - mid))
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            return end;
        }

        private boolean possible(int[] tasks, int[] workers, int pills, int strength, int assigned, int start) {
            int[] queue = new int[assigned];
            int right = 0, left = 0;
            for (int i = 0, j = 0; i < assigned; i++) {
                int curStrength = workers[start + i];
                if (right == left) {
                    if (curStrength >= tasks[j]) {
                        j++;
                        continue;
                    }
                    if (pills == 0)
                        return false;
                    curStrength += strength;
                    pills--;
                    while (j < assigned && curStrength >= tasks[j]) {
                        queue[right++] = tasks[j++];
                    }
                    if (left == right)
                        return false;
                    right--;
                } else {
                    if (curStrength >= queue[left]) {
                        left++;
                        continue;
                    }
                    if (pills == 0)
                        return false;
                    curStrength += strength;
                    pills--;
                    while (j < assigned && curStrength >= tasks[j]) {
                        queue[right++] = tasks[j++];
                    }
                    right--;
                }
            }
            return left == right;
        }
    }
}