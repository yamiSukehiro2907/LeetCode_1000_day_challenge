
public class GFG_11 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node node = new Node(2);
        solution.findMaxSum(node);
    }

    private static class Node {

        int data;
        Node left, right;

        public Node(int d) {
            data = d;
            left = right = null;
        }
    }

    private static class Solution {

        private int max;

        private int findMaxSum(Node root) {
            max = Integer.MIN_VALUE;
            find(root);
            return max;
        }

        private int find(Node root) {
            if (root == null) {
                return 0;
            }
            int left = find(root.left);
            int right = find(root.right);
            if (left <= 0 && right <= 0) {
                if (max < root.data) {
                    max = root.data;
                }
                return root.data;
            }
            int sum = root.data + Math.max(left, right);
            int includingRoot = root.data + left + right;
            if (max < includingRoot) {
                max = includingRoot;
            }
            if (max < sum) {
                max = sum;
            }
            return sum;
        }
    }
}
