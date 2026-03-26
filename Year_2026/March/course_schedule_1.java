package March;

import java.util.ArrayList;
import java.util.List;

public class course_schedule_1 {
    static void main() {

    }

    static class Solution {
        private List<List<Integer>> adjList;

        public boolean canFinish(int n, int[][] prerequisites) {
            this.adjList = new ArrayList<>();
            for (int i = 0; i < n; i++) this.adjList.add(new ArrayList<>());
            for (int[] pre : prerequisites) this.adjList.get(pre[1]).add(pre[0]);
            return canCoverAll(n);
        }

        private boolean canCoverAll(int n) {
            int[] visited = new int[n];
            for (int i = 0; i < n; i++) if (visited[i] == 0 && isCycle(visited, i)) return false;
            return true;
        }

        private boolean isCycle(int[] visited, int src) {
            visited[src] = 1;
            for (int next : adjList.get(src)) {
                if (visited[next] == 1) return true;
                if (visited[next] == 0 && isCycle(visited, next)) return true;
            }
            visited[src] = 2;
            return false;
        }
    }
}
