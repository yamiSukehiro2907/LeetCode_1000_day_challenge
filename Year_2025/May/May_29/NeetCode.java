package May.May_29;

import java.util.*;

public class NeetCode {
    class LinkedList {

        private class Node {
            Node next;
            int val;

            Node(int val) {
                this.val = val;
                this.next = null;
            }
        }

        private Node head;
        private Node tail;
        private int size;

        public LinkedList() {
            this.head = this.tail = null;
            this.size = 0;
        }

        public int get(int index) {
            if (size == 0) {
                return -1;
            }
            if (index >= size) {
                return -1;
            }
            Node temp = head;
            int i = 0;
            while (i < index) {
                i++;
                temp = temp.next;
            }
            return temp.val;
        }

        public void insertHead(int val) {
            Node newhead = new Node(val);
            size++;
            if (size == 0) {
                this.head = newhead;
                this.tail = newhead;
                return;
            }
            newhead.next = head;
            head = newhead;
        }

        public void insertTail(int val) {
            Node newTail = new Node(val);
            size++;
            if (size == 0) {
                this.head = this.tail = newTail;
                return;
            }

            tail.next = newTail;
            tail = tail.next;
        }

        public boolean remove(int index) {
            if (index > size) {
                return false;
            }
            if (size == 0) {
                return false;
            }
            if (index == 0) {
                head = head.next;
                if (size == 1) {
                    tail = null;
                }
                size--;
                return true;
            }
            Node curr = head;
            Node prev = null;
            int i = 0;
            while (i < index) {
                prev = curr;
                curr = head.next;
                i++;
            }
            if (curr == null) {
                prev.next = null;
                return true;
            }
            prev.next = curr.next;
            return true;
        }

        public ArrayList<Integer> getValues() {
            Node temp = head;
            ArrayList<Integer> list = new ArrayList<>();
            while (temp != null) {
                list.add(temp.val);
                temp = temp.next;
            }
            return list;
        }
    }

}
/*
 * ["insertTail", 1, "insertTail", 2, "get", 1, "remove", 1, "insertTail", 2,
 * "get", 1, "get", 0]
 */