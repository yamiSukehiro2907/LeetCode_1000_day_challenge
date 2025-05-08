https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/

```
class Solution {
public:
    pair<TreeNode*, int> solve(TreeNode* root) {
        if (!root) return {nullptr, 0};
        auto left = solve(root->left);
        auto right = solve(root->right);
        if (left.second > right.second) return {left.first, left.second + 1};
        if (right.second > left.second) return {right.first, right.second + 1};
        return {root, left.second + 1};
    }
    
    TreeNode* lcaDeepestLeaves(TreeNode* root) {
        auto ans = solve(root);
        return ans.first;
    }
};

```