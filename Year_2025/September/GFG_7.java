public class GFG_7 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = null;
        System.out.println(solution.mergeKLists(new Node[] { head }));
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Solution {
        public Node mergeKLists(Node[] arr) {
            Node head = new Node(Integer.MIN_VALUE);
            for (Node node : arr)
                head = merge(node, head);
            return head.next;
        }

        public Node merge(Node n1, Node n2) {
            if (n1 == null)
                return n2;
            if (n2 == null)
                return n1;
            if (n1.data < n2.data) {
                n1.next = merge(n1.next, n2);
                return n1;
            } else {
                n2.next = merge(n1, n2.next);
                return n2;
            }
        }
    }
}