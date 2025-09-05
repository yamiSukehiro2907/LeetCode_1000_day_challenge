
public class GFG_5 {
    public static void main(String[] args) {
        int[] arr = { 0, 2 };
        print(create(arr));
        Solution solution = new Solution();
        print(solution.segregate(create(arr)));
    }

    static Node create(int[] arr) {
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    static class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Solution {
        public Node segregate(Node head) {
            if (head == null || head.next == null)
                return head;
            Node n0 = null;
            Node n1 = null;
            Node n2 = null;
            Node n0Last = null;
            Node n1Last = null;
            while (head != null) {
                Node node = head;
                head = head.next;
                if (node.data == 0) {
                    if (n0 == null) {
                        n0Last = node;
                    }
                    node.next = n0;
                    n0 = node;
                } else if (node.data == 1) {
                    if (n1 == null) {
                        n1Last = node;
                    }
                    node.next = n1;
                    n1 = node;
                } else {
                    node.next = n2;
                    n2 = node;
                }
            }
            System.out.print("Node-0: ");
            print(n0);
            System.out.print("Node-1: ");
            print(n1);
            System.out.print("Node-2: ");
            print(n2);
            Node ans;
            if (n0 != null) {
                ans = n0;
                if (n1 != null) {
                    n0Last.next = n1;
                    if (n2 != null) {
                        n1Last.next = n2;
                    }
                } else if (n2 != null) {
                    n0Last.next = n2;
                }
            } else if (n1 != null) {
                ans = n1;
                if (n2 != null) {
                    n1Last.next = n2;
                }
            } else
                ans = n2;

            return ans;
        }
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }
}
