package February;

public class sum_of_root_to_leaf_binary_numbers {
    static void main() {

    }

    static class Solution {
        public int sumRootToLeaf(TreeNode root) {
            return solve(root, 0);
        }

        private int solve(TreeNode root, int currSum) {
            if (root == null) return 0;
            currSum <<= 1;
            currSum |= root.val;
            if (root.left == null && root.right == null) return currSum;
            return solve(root.left, currSum) + solve(root.right, currSum);
        }
    }
}
