
import java.util.LinkedList;
import java.util.Queue;

public class GFG_8 {
    public static void main(String[] args) {
        int pre[] = { 1, 2, 4 };
        int post[] = { 2, 4 ,1};
        Solution solution = new Solution();
        Node root = solution.constructTree(pre, post);
        print(root);
    }

    private static class Solution {
        private int postIndex;
        private int preIndex;
        private int[] pre;
        private int[] post;

        public Node constructTree(int[] pre, int[] post) {
            this.pre = pre;
            this.post = post;
            postIndex = preIndex = 0;
            return create();
        }

        private Node create() {
            if (preIndex >= pre.length) return null;
            Node node = new Node(pre[preIndex++]);
            if(node.data != post[postIndex]) node.left = create();
            if(node.data != post[postIndex]) node.right = create();
            postIndex++;
            return node;
        }

    }

    private static void print(Node root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        System.out.println(root.data);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    System.out.print(node.left.data + " ");
                } else {
                    System.out.print("N ");
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    System.out.print(node.right.data + " ");
                } else {
                    System.out.print("N ");
                }
            }
            System.out.println();
        }
    }

    private static class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }
}