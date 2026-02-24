def sum_root_to_leaf(root)
  solve(root , 0)
end

def solve(root , currSum)
  return 0 if root == nil
  currSum <<= 1
  currSum |= root.val
  return currSum if root.left == nil && root.right == nil
  solve(root.left , currSum) + solve(root.right , currSum)
end