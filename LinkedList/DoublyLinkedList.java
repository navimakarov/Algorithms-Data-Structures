public class DoublyLinkedList {
    private class Node {
        int data;
        Node next, prev;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head = null, tail = null;

    public void push_back(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void push_forward(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
    }

    public boolean remove(int data) {
        Node current = head;

        if(current != null && current.data == data) {
            current = current.next;
            if(current != null)
                current.prev = null;

            head = current;
            return true;
        }

        while(current != null && current.data != data) {
            current = current.next;
        }

        if(current == null) {
            System.out.println("Index out of bounds");
            return false;
        }
        current.prev.next = current.next;
        if(current.next != null)
            current.next.prev = current.prev;

        return true;
    }

    public void reverse() {
        Node current = head;
        Node prev = null;

        while(current != null) {
            prev = current.prev;

            current.prev = current.next;
            current.next = prev;

            current = current.prev;
        }
        tail = head;
        head = prev.prev;
    }

}
