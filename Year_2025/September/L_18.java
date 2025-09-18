import java.util.*;

public class L_18 {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        int[][] arr = { { 1, 101, 10 }, { 2, 102, 20 }, { 3, 103, 15 } };
        for (int[] edge : arr) {
            List<Integer> temp = new ArrayList<>();
            temp.add(edge[0]);
            temp.add(edge[1]);
            temp.add(edge[2]);
            list.add(temp);
        }

        TaskManager taskManager = new TaskManager(list);
        taskManager.add(1, 102, 15);
        taskManager.edit(102, 100);
        taskManager.rmv(103);
        System.out.println(taskManager.execTop());
    }

    private static class TaskManager {
        int[] priorities = new int[100001];
        int[] userIds = new int[100001];
        PriorityQueue<Long> PQ = new PriorityQueue<>((a, b) -> Long.compare(b, a));

        public TaskManager(List<List<Integer>> tasks) {
            for (List<Integer> task : tasks) {
                int userId = task.get(0);
                int taskId = task.get(1);
                int priority = task.get(2);
                priorities[taskId] = priority;
                userIds[taskId] = userId;
                PQ.offer((long) priority * 100001 + taskId);
            }
        }

        public void add(int userId, int taskId, int priority) {
            if (priorities[taskId] > 0)
                return;
            priorities[taskId] = priority;
            userIds[taskId] = userId;
            PQ.offer((long) priority * 100001 + taskId);
        }

        public void edit(int taskId, int newPriority) {
            priorities[taskId] = newPriority;
            PQ.offer((long) newPriority * 100001 + taskId);
        }

        public void rmv(int taskId) {
            priorities[taskId] = -1;
        }

        public int execTop() {
            while (!PQ.isEmpty()) {
                long current = PQ.poll();
                int taskId = (int) (current % 100001);
                int priority = (int) (current / 100001);
                if (priorities[taskId] != priority)
                    continue;
                priorities[taskId] = -1;
                return userIds[taskId];
            }
            return -1;
        }
    }
/*
class TaskManager {
    private final PriorityQueue<Task> tasksWithUser;
    private final Map<Integer, Task> recentTasks;

    public TaskManager(List<List<Integer>> tasks) {
        this.tasksWithUser = new PriorityQueue<>(new TaskSort());
        this.recentTasks = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            List<Integer> temp = tasks.get(i);
            int userId = temp.get(0);
            int taskId = temp.get(1);
            int priority = temp.get(2);
            Task task = new Task(taskId, userId, priority);
            recentTasks.put(taskId, task);
            tasksWithUser.offer(task);
        }
    }

    public void add(int userId, int taskId, int priority) {
        Task task = new Task(taskId, userId, priority);
        tasksWithUser.add(task);
        recentTasks.put(taskId, task);
    }

    public void edit(int taskId, int newPriority) {
        Task task = recentTasks.get(taskId);
        task.inUse = -1;
        Task newTask = new Task(taskId, task.userId, newPriority);
        tasksWithUser.offer(newTask);
        recentTasks.put(taskId, newTask);
    }

    public void rmv(int taskId) {
        recentTasks.get(taskId).inUse = -1;
    }

    public int execTop() {
        while (!tasksWithUser.isEmpty()) {
            Task highestPriorityTask = tasksWithUser.poll();
            if (valid(highestPriorityTask)) {
                return highestPriorityTask.userId;
            }
        }
        return -1;
    }

    private boolean valid(Task highestPriorityTask) {
        if (recentTasks.get(highestPriorityTask.taskId).inUse == -1) {
            return false;
        }
        return recentTasks.get(highestPriorityTask.taskId).equals(highestPriorityTask);
    }

    private class Task {
        int taskId;
        int userId;
        int priority;
        int inUse;

        public Task(int taskId, int userId, int priority) {
            this.taskId = taskId;
            this.userId = userId;
            this.priority = priority;
            this.inUse = 1;
        }
        
        @Override
        public boolean equals(Task other){
            return this.taskId == other.taskId && this.userId == other.userId && this.priority == other.priority && this.inUse = other.inUse;
        }
    }

    private class TaskSort implements Comparator<Task> {
        @Override
        public int compare(Task a, Task b) {
            return a.priority == b.priority ? b.taskId - a.taskId : b.priority - a.priority;
        }
    }
} 
*/
}
