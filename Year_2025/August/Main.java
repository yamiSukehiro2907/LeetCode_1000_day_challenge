import java.util.*;

public class Main {
    public static void main(String[] args) {

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
        for (int i = 0; i < 4; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1); // 0 -> 1
        graph.get(1).add(0); // 1 -> 0
        graph.get(1).add(2); // 1 -> 2
        graph.get(2).add(1); // 2 -> 1
        graph.get(2).add(3); // 2 -> 3
        graph.get(3).add(2); // 3 -> 2
    }

    public boolean isConnectedBfs(int source, int destination) {
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

    public boolean isConnectedDfs() {

    }
}
