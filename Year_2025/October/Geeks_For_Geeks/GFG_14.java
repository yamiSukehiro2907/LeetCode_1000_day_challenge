public class GFG_14 {
    public static void main(String[] args) {
        
    }

    private static class Node{
        int data;
        Node left;
        Node right;
    }

    private static class Solution {
        public int nodeSum(Node root, int l, int r) {
            if (root == null) return 0;
            int sum = 0;
            if (root.data >= l && root.data <= r) sum += root.data;
            if (root.data >= l) sum += nodeSum(root.left, l, r);
            if (root.data <= r) sum += nodeSum(root.right, l, r);
            return sum;
        }
    }

}