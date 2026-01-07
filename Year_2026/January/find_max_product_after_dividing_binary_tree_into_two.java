package January;

public class find_max_product_after_dividing_binary_tree_into_two {

    static void main() {

    }

    static class Solution {
        private long maxProduct = 0;
        private long totalSum = 0;

        public int maxProduct(TreeNode root) {
            if (root == null) return 0;
            findTotalSum(root);
            find(root);
            return (int) (maxProduct % 1_000_000_007L);
        }

        private void findTotalSum(TreeNode root) {
            if (root == null) return;
            totalSum += root.val;
            findTotalSum(root.left);
            findTotalSum(root.right);
        }

        private long find(TreeNode root) {
            long leftSum = 0L, rightSum = 0L;
            if (root.left != null) {
                leftSum = find(root.left);
                long separate = (totalSum - leftSum) * leftSum;
                if (separate > maxProduct) maxProduct = separate;
            }
            if (root.right != null) {
                rightSum = find(root.right);
                long separate = (totalSum - rightSum) * rightSum;
                if (separate > maxProduct) maxProduct = separate;
            }
            return (long) root.val + leftSum + rightSum;
        }
    }
}
