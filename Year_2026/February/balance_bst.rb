class TreeNode
    attr_accessor :val, :left, :right
    def initialize(val = 0, left = nil, right = nil)
        @val = val
        @left = left
        @right = right
    end
end

def balance_bst(root)
    nodes = []
    fill(root , nodes)
    return build(nodes , 0 , nodes.length - 1)
end

def fill(root , nodes)
    return nil unless root
    fill(root.left , nodes)
    nodes.push(root)
    fill(root.right , nodes)
end

def build(nodes , left , right)
    return nil if left > right
    mid = left + ((right - left) >> 1)
    root = nodes[mid]
    root.left = build(nodes , left , mid - 1)
    root.right = build(nodes , mid + 1 , right)
    return root
end
