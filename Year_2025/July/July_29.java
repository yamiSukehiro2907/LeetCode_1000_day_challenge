import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class July_29 {
    public static void main(String[] args) {

    }

    static class Solution {
        static class Node {
            Map<String, Node> children = new TreeMap<>();
            String serialization = "";
            boolean toDelete = false;

            void markForDeletion() {
                if (toDelete)
                    return;
                toDelete = true;
                for (Node child : children.values()) {
                    child.markForDeletion();
                }
            }
        }

        private final ForkJoinPool threadPool;
        private static final int PARALLEL_THRESHOLD = 500;

        public Solution() {
            this.threadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        }

        public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
            if (paths.isEmpty())
                return new ArrayList<>();

            try {
                if (paths.size() >= PARALLEL_THRESHOLD) {
                    return threadPool.submit(() -> solveProblemParallel(paths)).get();
                } else {
                    return solveProblemSequential(paths);
                }
            } catch (InterruptedException | ExecutionException e) {
                return solveProblemSequential(paths);
            }
        }

        private List<List<String>> solveProblemParallel(List<List<String>> paths) {
            Node root = new Node();
            Map<List<String>, Node> pathToNode = new HashMap<>();
            for (List<String> path : paths) {
                Node current = root;
                for (String folder : path) {
                    current.children.putIfAbsent(folder, new Node());
                    current = current.children.get(folder);
                }
                pathToNode.put(path, current);
            }
            generateSerializationsParallel(root);
            markDuplicatesParallel(root);
            return paths.parallelStream()
                    .filter(path -> !pathToNode.get(path).toDelete)
                    .collect(Collectors.toList());
        }

        private void generateSerializationsParallel(Node root) {
            List<Node> allNodes = new ArrayList<>();
            collectAllNodes(root, allNodes);
            Map<Integer, List<Node>> nodesByDepth = new HashMap<>();
            assignDepthsAndGroup(root, 0, nodesByDepth);
            List<Integer> depths = new ArrayList<>(nodesByDepth.keySet());
            depths.sort(Collections.reverseOrder());
            for (Integer depth : depths) {
                List<Node> nodesAtDepth = nodesByDepth.get(depth);
                if (nodesAtDepth.size() > 10) {
                    nodesAtDepth.parallelStream().forEach(this::generateSerialization);
                } else {
                    nodesAtDepth.forEach(this::generateSerialization);
                }
            }
        }

        private void generateSerialization(Node node) {
            if (node.children.isEmpty()) {
                node.serialization = "";
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Node> entry : node.children.entrySet()) {
                sb.append(entry.getKey())
                        .append('(')
                        .append(entry.getValue().serialization)
                        .append(')');
            }
            node.serialization = sb.toString();
        }

        private void assignDepthsAndGroup(Node node, int depth, Map<Integer, List<Node>> nodesByDepth) {
            nodesByDepth.computeIfAbsent(depth, k -> new ArrayList<>()).add(node);
            for (Node child : node.children.values()) {
                assignDepthsAndGroup(child, depth + 1, nodesByDepth);
            }
        }

        private void collectAllNodes(Node node, List<Node> allNodes) {
            allNodes.add(node);
            for (Node child : node.children.values()) {
                collectAllNodes(child, allNodes);
            }
        }

        private void markDuplicatesParallel(Node root) {
            List<Node> nonEmptyNodes = new ArrayList<>();
            collectNonEmptyNodes(root, nonEmptyNodes);
            Map<String, List<Node>> serializationGroups = nonEmptyNodes.parallelStream()
                    .filter(node -> !node.serialization.isEmpty())
                    .collect(Collectors.groupingByConcurrent(node -> node.serialization));
            serializationGroups.entrySet().parallelStream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .forEach(entry -> {
                        for (Node duplicate : entry.getValue()) {
                            duplicate.markForDeletion();
                        }
                    });
        }

        private void collectNonEmptyNodes(Node node, List<Node> nonEmptyNodes) {
            if (!node.children.isEmpty()) {
                nonEmptyNodes.add(node);
            }
            for (Node child : node.children.values()) {
                collectNonEmptyNodes(child, nonEmptyNodes);
            }
        }

        private List<List<String>> solveProblemSequential(List<List<String>> paths) {
            if (paths.isEmpty())
                return new ArrayList<>();
            Node root = new Node();
            Map<List<String>, Node> pathToNode = new HashMap<>();
            for (List<String> path : paths) {
                Node current = root;
                for (String folder : path) {
                    current.children.putIfAbsent(folder, new Node());
                    current = current.children.get(folder);
                }
                pathToNode.put(path, current);
            }
            generateSerializationsSequential(root);
            Map<String, List<Node>> serializationToNodes = new HashMap<>();
            collectSerializations(root, serializationToNodes);
            for (List<Node> duplicates : serializationToNodes.values()) {
                if (duplicates.size() > 1) {
                    for (Node node : duplicates) {
                        node.markForDeletion();
                    }
                }
            }
            List<List<String>> result = new ArrayList<>();
            for (List<String> path : paths) {
                if (!pathToNode.get(path).toDelete) {
                    result.add(path);
                }
            }

            return result;
        }

        private String generateSerializationsSequential(Node node) {
            if (node.children.isEmpty()) {
                node.serialization = "";
                return "";
            }

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Node> entry : node.children.entrySet()) {
                String childSerialization = generateSerializationsSequential(entry.getValue());
                sb.append(entry.getKey()).append('(').append(childSerialization).append(')');
            }

            node.serialization = sb.toString();
            return node.serialization;
        }

        private void collectSerializations(Node node, Map<String, List<Node>> serializationToNodes) {
            if (!node.serialization.isEmpty()) {
                serializationToNodes.computeIfAbsent(node.serialization, k -> new ArrayList<>()).add(node);
            }

            for (Node child : node.children.values()) {
                collectSerializations(child, serializationToNodes);
            }
        }

        public void shutdown() {
            if (threadPool != null && !threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        }
    }
}
