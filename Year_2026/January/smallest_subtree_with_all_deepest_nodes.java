package January;

public class smallest_subtree_with_all_deepest_nodes {
    static void main() {

    }

    static class Solution {
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            return solve(root).node;
        }

        private TreeData solve(TreeNode root) {
            if (root == null) return new TreeData(null, 0);
            TreeData left = solve(root.left);
            TreeData right = solve(root.right);
            if (left.depth > right.depth) return new TreeData(left.node, left.depth + 1);
            if (right.depth > left.depth) return new TreeData(right.node, right.depth + 1);
            return new TreeData(root, left.depth + 1);
        }

        static class TreeData {
            TreeNode node;
            int depth;

            TreeData(TreeNode node, int depth) {
                this.node = node;
                this.depth = depth;
            }
        }
    }
}
