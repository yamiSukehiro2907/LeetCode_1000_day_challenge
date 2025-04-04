https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/

```
class Solution:
    def lcaDeepestLeaves(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        def solve(node: Optional[TreeNode]) -> (Optional[TreeNode], int):
            if not node:
                return None, 0
            
            leftNode, leftDepth = solve(node.left)
            rightNode, rightDepth = solve(node.right)
            
            if leftDepth > rightDepth:
                return leftNode, leftDepth + 1
            if rightDepth > leftDepth:
                return rightNode, rightDepth + 1
            return node, leftDepth + 1
        
        result, _ = solve(root)
        return result

```