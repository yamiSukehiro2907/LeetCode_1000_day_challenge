@SuppressWarnings("unused")
public class July_14 {
    public static void main(String[] args) {

    }

    static
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public int getDecimalValue(ListNode head) {
            int result = 0;
            int count = findLength(head) - 1;
            ListNode temp = head;
            while (temp != null) {
                result += (temp.val << (count--));
                temp = temp.next;
            }
            return result;
        }

        private int findLength(ListNode head) {
            if (head == null)
                return 0;
            return findLength(head.next) + 1;
        }
    }
}
