https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/

```
func lcaDeepestLeaves(root *TreeNode) *TreeNode {
    node, _ := solve(root)
    return node
}

func solve(root *TreeNode) (*TreeNode, int) {
    if root == nil {
        return nil, 0
    }
    leftNode, leftDepth := solve(root.Left)
    rightNode, rightDepth := solve(root.Right)
    if leftDepth > rightDepth {
        return leftNode, leftDepth + 1
    }
    if rightDepth > leftDepth {
        return rightNode, rightDepth + 1
    }
    return root, leftDepth + 1
}

```