import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        System.out.println(graph.isConnectedDfs(0, 4));
        System.out.println(graph.isConnectedBfs(0, 4));
    }
}

class Graph {
    int size;
    List<List<Integer>> graph;

    public Graph(int size) {
        this.size = size;
        init();
    }

    private void init() {
        this.graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 1; i < size; i++) {
            graph.get(i).add(i - 1);
            graph.get(i - 1).add(i);
        }
    }

    public boolean isConnectedDfs(int source, int destination) {
        boolean[] visited = new boolean[graph.size()];
        visited[source] = true;
        return findConnection(visited, source, destination);
    }

    private boolean findConnection(boolean[] visited, int source, int destination) {
        visited[source] = true;
        for (int node : graph.get(source)) {
            if (node == destination) {
                return true;
            } else if (!visited[node]) {
                return findConnection(visited, node, destination);
            }
        }
        return false;
    }

    public boolean isConnectedBfs(int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int num : graph.get(node)) {
                if (!set.contains(num)) {
                    if (num == destination)
                        return true;
                    queue.add(num);
                    set.add(num);
                }
            }
        }
        return false;
    }
}
