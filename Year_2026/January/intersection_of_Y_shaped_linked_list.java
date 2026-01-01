package January;

public class intersection_of_Y_shaped_linked_list {
    static void main() {
        Node head = new Node(1);
        head.next = new Node(2);

        Node head2 = new Node(3);
        head2.next = new Node(4);

        Node intersection = new Node(7);
        intersection.next = new Node(8);

        head.next.next = intersection;
        head2.next.next = intersection;

        System.out.print("Head1: ");
        head.print();
        System.out.print("Head2: ");
        head2.print();

        Solution s = new Solution();
        Node ans = s.intersectPoint(head, head2);
        System.out.print("Intersection Point: ");
        ans.print();
    }

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

        void print() {
            Node temp = this;
            while (temp != null) {
                System.out.print(temp.data + " ");
                if (temp.next != null) System.out.print(" -> ");
                temp = temp.next;
            }
            System.out.println();
        }

    }

    static class Solution {
        public Node intersectPoint(Node head1, Node head2) {
            Node p1 = head1, p2 = head2;
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
                if (p1 == null) p1 = head1;
                if (p2 == null) p2 = head2;
            }
            return p1;
        }
    }
}