package February;
import java.util.*;

public class balance_bst {
   public static void main(String[] args){
      
   }
   static class Solution {
    private List<TreeNode> nodes;
    public TreeNode balanceBST(TreeNode root) {
        this.nodes = new ArrayList<>();
        fill(root);
        return build(0 , nodes.size() - 1);
    }
    private void fill(TreeNode root){
        if(root == null) return;
        fill(root.left);
        nodes.add(root);
        fill(root.right);
    }
    private TreeNode build(int start , int end){
        if(start > end) return null;
        int mid = start + ((end - start) >> 1);
        TreeNode root = nodes.get(mid);
        root.left = build(start , mid - 1);
        root.right = build(mid + 1 , end);
        return root;
    }
}
}
