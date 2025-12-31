package December.GFG;

public class add_two_linked_lists {
    static void main() {
        Node num1 = new Node(-1);
        Node num2 = new Node(0);
        Solution sol = new Solution();
        sol.addTwoLists(num1, num2).print();
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        void print() {
            Node node = this;
            while (node != null) {
                System.out.print(node.data);
                if (node.next != null) System.out.print(" -> ");
                node = node.next;
            }
            System.out.println();
        }
    }

    static class Solution {
        public Node addTwoLists(Node head1, Node head2) {
            head1.print();
            head2.print();
            return removeTrailingZeroes(reverse(findSum(reverse(head1), reverse(head2), 0)));
        }

        private Node removeTrailingZeroes(Node head) {
            while (head != null && head.data == 0) head = head.next;
            return head;
        }

        private Node reverse(Node head) {
            Node prev = null;
            while (head != null) {
                Node node = head;
                head = head.next;
                node.next = prev;
                prev = node;
            }
            return prev;
        }

        private Node findSum(Node node1, Node node2, int carry) {
            if (node1 == null && node2 == null && carry == 0) return null;
            Node head = null;
            if (node1 != null) {
                carry += node1.data;
                head = node1;
                node1 = node1.next;
                head.next = null;
            }
            if (node2 != null) {
                carry += node2.data;
                if (head == null) head = node2;
                node2 = node2.next;
                head.next = null;
            }
            if (head == null) head = new Node(-1);
            head.data = carry % 10;
            head.next = findSum(node1, node2, carry / 10);
            return head;
        }
    }
}
