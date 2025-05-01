import java.util.*;

class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int task_performed = 0;
        int taskIndex = tasks.length - 1;
        boolean[] workers_used = new boolean[workers.length];
        int workersIndex = workers.length - 1;
        // By this we will be able to assign workers than can perform tasks without
        while (taskIndex >= 0) {
            if (!inRange(workersIndex, workers) && workers[workersIndex] < tasks[taskIndex]) {
                break; // if the end index of workers strength is less than our tasks required index
                // then we cannot assign any task now without any pills
            }
            int index = binary_search(workers, tasks[taskIndex], workersIndex);
            if (index == -1) {
                break; // that means we were not able to get any worker with equal or greater than
                       // required tasks
            } else {
                // we got any index
                System.out.println(
                        "Worker: " + workers[index] + "(Worker_index: " + index + ") , " + "Task: "
                                + tasks[taskIndex]
                                + "(Task_index: " + taskIndex + " )");
                workers_used[index] = true;
                workersIndex = index - 1;
                task_performed++;
            }
            taskIndex--;
        }
        int i = workers.length - 1;
        while (i >= 0 && taskIndex >= 0) {
            if (pills == 0) {
                return task_performed;
            }
            if (workers_used[i]) {
                i--;
                continue;
            } else {
                int currTask = tasks[taskIndex];
                if (currTask > workers[i] + strength) {
                    return task_performed;
                } else {
                    System.out.println("Used with strength");
                    System.out.println(
                            "Worker: " + workers[i] + "(Worker_index: " + i + ") , " + "Task: "
                                    + tasks[taskIndex]
                                    + "(Task_index: " + i + " )");
                    task_performed++;
                    taskIndex--;
                    pills--;
                }
            }
            i--;
        }
        return task_performed;
    }

    private int binary_search(int[] workers, int target, int rightHandRange) {
        int start = 0;
        int end = rightHandRange;
        while (start < end) {
            int mid = start + ((end - start) / 2);
            if (workers[mid] >= target) {
                if (mid < rightHandRange && workers[mid] == workers[mid + 1]) {
                    while (mid < rightHandRange && workers[mid] == workers[mid + 1]) {
                        mid++;
                    }
                }
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (start > rightHandRange || (start <= rightHandRange && workers[start] < target))
            return -1;
        return start;
    }

    private boolean inRange(int index, int[] workers) {
        return index >= 0 && workers.length > index;
    }
}

public class Max_tasks_can_be_assigned {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] workers = { 0, 3, 3 };
        int[] tasks = { 3, 2, 1 };
        int pills = 1;
        int strength = 1;
        int tasks_assigned = solution.maxTaskAssign(tasks, workers, pills, strength);
        System.out.println(tasks_assigned);
    }
}