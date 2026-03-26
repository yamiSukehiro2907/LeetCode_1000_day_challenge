package March;

import java.util.*;

public class minimum_height_roots {
    static void main() {

    }

    static class Solution {
        private List<List<Integer>> list;

        public ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
            this.list = new ArrayList<>();
            for (int i = 0; i < V; i++) this.list.add(new ArrayList<>());
            for (int[] edge : edges) {
                this.list.get(edge[0]).add(edge[1]);
                this.list.get(edge[1]).add(edge[0]);
            }
            int A = findFarthest(0);
            int B = findFarthest(A);
            List<Integer> diameter = getDiameter(A, B);
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(diameter.get(diameter.size() / 2));
            if (diameter.size() % 2 == 0) temp.add(diameter.get((diameter.size() / 2) - 1));
            return temp;
        }

        private int findFarthest(int src) {
            boolean[] visited = new boolean[list.size()];
            visited[src] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(src);
            int farthestNode = src;
            while (!queue.isEmpty()) {
                farthestNode = queue.peek();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int node = queue.poll();
                    for (int next : list.get(node)) {
                        if (!visited[next]) {
                            queue.offer(next);
                            visited[next] = true;
                        }
                    }
                }
            }
            return farthestNode;
        }

        private List<Integer> getDiameter(int start, int end) {
            int[] parent = new int[list.size()];
            Arrays.fill(parent, -1);
            boolean[] visited = new boolean[list.size()];
            visited[start] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int next : list.get(node)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        parent[next] = node;
                        queue.add(next);
                    }
                }
            }
            List<Integer> path = new ArrayList<>();
            for (int cur = end; cur != -1; cur = parent[cur]) path.add(cur);
            Collections.reverse(path);
            return path;
        }
    }
}
