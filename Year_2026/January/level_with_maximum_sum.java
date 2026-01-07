package January;

public class level_with_maximum_sum {
    static void main() {

    }



    static class Solution {
        private int maxLevel = 0;
        private static final int[] levelSum = new int[10001];

        public int maxLevelSum(TreeNode root) {
            if (root == null) return 0;
            dfs(root, 0);
            int maxSum = levelSum[0], level = 0;
            for (int i = 0; i <= maxLevel; i++) {
                if (levelSum[i] > maxSum) {
                    maxSum = levelSum[i];
                    level = i;
                }
                levelSum[i] = 0;
            }
            return level + 1;
        }

        private void dfs(TreeNode root, int currLevel) {
            if (root == null) return;
            if (currLevel > maxLevel) maxLevel = currLevel;
            levelSum[currLevel] += root.val;
            dfs(root.left, currLevel + 1);
            dfs(root.right, currLevel + 1);
        }
    }
}
