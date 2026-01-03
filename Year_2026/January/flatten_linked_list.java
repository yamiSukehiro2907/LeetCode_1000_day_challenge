package January;

public class flatten_linked_list {
    static void main() {
        Solution sol = new Solution();
        Node root = new Node(1);
        root.next = new Node(12);
        root.next.next = new Node(20);
        root.bottom = new Node(13);
        print(root);

        Node ans =  sol.flatten(root);
        print(ans);
    }

    static void print(Node head) {
        if(head == null) {
            System.out.println();
            return;
        }
        System.out.print(head.data);
        if(head.bottom != null) System.out.print(" -> ");
        print(head.bottom);
    }

    static class Node {
        int data;
        Node next;
        Node bottom;

        Node(int data) {
            this.data = data;
            this.bottom = null;
            this.next = null;
        }
    }

    static class Solution {
        public Node flatten(Node root){
            if(root == null || root.next == null) return root;
            root.next = flatten(root.next);
            return merge(root , root.next);
        }

        private static Node merge(Node l1 , Node l2){
            if(l1 == null) return l2;
            if(l2 == null) return l1;
            if(l1.data < l2.data){
                l1.bottom = merge(l1.bottom, l2);
                return l1;
            }
            l2.bottom = merge(l1, l2.bottom);
            return l2;
        }
    }
}
