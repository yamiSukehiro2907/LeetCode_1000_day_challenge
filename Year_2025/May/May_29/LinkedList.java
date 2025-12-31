package May.May_29;

import java.util.ArrayList;

public class LinkedList {
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
        if (size == 0 || index < 0 || index >= size) {
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
        if (size == 0) {
            this.head = newhead;
            this.tail = newhead;
        } else {
            newhead.next = head;
            head = newhead;
        }
        size++;
    }

    public void insertTail(int val) {
        Node newTail = new Node(val);
        if (size == 0) {
            this.head = this.tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public boolean remove(int index) {
        if (size == 0 || index < 0 || index >= size) {
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
            curr = curr.next;
            i++;
        }
        prev.next = curr.next;
        if (curr == tail) {
            tail = prev;
        }
        size--;
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