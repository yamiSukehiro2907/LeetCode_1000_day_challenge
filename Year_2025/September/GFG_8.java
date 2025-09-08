public class GFG_8 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = { 3, 4, 5, 1, 2 };
        Node head = create(arr);
        System.out.println("Head Node: ");
        print(head);
        Node ans = solution.mergeSort(head);
        System.out.println("Sorted node: ");
        print(ans);
    }

    private static class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private static Node create(int[] arr) {
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            temp.next = node;
            temp = temp.next;
        }
        return head;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    private static class Solution {
        public Node mergeSort(Node head) {
            if (head == null || head.next == null)
                return head;
            Node slow = head;
            Node fast = head.next;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            System.out.println("Head: ");
            print(head);
            System.out.println("First: ");
            print(head);
            System.out.println("Second: ");
            print(slow.next);
            Node right = mergeSort(slow.next);
            slow.next = null;
            System.out.println("Right: ");
            print(right);
            Node left = mergeSort(head);
            System.out.println("Left: ");
            print(left);
            return merge(left, right);
        }

        private Node merge(Node left, Node right) {
            if (left == null)
                return right;
            if (right == null)
                return left;
            if (left.data < right.data) {
                left.next = merge(left.next, right);
                return left;
            } else {
                right.next = merge(left, right.next);
                return right;
            }
        }
    }
}