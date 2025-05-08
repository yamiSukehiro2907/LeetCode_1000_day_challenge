https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/

```
class Solution {
    private class TreeData{
        TreeNode node;
        int depth;
        TreeData(TreeNode node , int depth){
            this.node = node;
            this.depth = depth;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return solve(root).node;
    }
    private TreeData solve(TreeNode root){
        if(root == null) return new TreeData(null , 0);
        TreeData left = solve(root.left);
        TreeData right = solve(root.right);
        if(left.depth > right.depth) return new TreeData(left.node , left.depth + 1);
        if(left.depth < right.depth) return new TreeData(right.node , right.depth + 1);
        return new TreeData(root , left.depth + 1);
    }
}
```