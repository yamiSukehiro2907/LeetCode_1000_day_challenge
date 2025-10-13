
public class GFG_13 {
    public static void main(String[] args) {

    }

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    private static class Solution {
        public int getMaxSum(Node root) {
            Pair pair = solve(root);
            return Math.max(pair.include, pair.exclude);
        }

        private Pair solve(Node root) {
            if (root == null)
                return new Pair(0, 0);
            Pair left = solve(root.left);
            Pair right = solve(root.right);
            int include = left.exclude + right.exclude + root.data;
            int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);
            return new Pair(include, exclude);
        }

        private class Pair {
            int include;
            int exclude;

            public Pair(int include, int exclude) {
                this.include = include;
                this.exclude = exclude;
            }
        }
    }
}
