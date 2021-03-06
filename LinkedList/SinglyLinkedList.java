public class SinglyNode {
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head = null, tail = null;

    public void add(int data) {
        Node newNode = new Node(data);

        if(head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public boolean remove(int data) {
        Node current = head;
        Node previous = null;

        if(current != null && current.data == data) {
            head = current.next;
            return true;
        }

        while(current != null && current.data != data) {
            previous = current;
            current = current.next;
        }

        if(current == null) {
            System.out.println("Node not found");
            return false;
        }

        previous.next = current.next;
        return true;
    }

    public void display() {
        Node current = head;
        if(current == null) {
            System.out.println("Empty list");
        }

        while(current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int size() {
        Node current = head;
        int size = 0;

        if(current == null) {
            return size;
        }

        while(current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public boolean removeByPosition(int position) {
        Node current = head, previous = null;

        if(position >= size() || position < 0) {
            System.out.println("Index out of bounds");
            return false;
        }

        if(position == 0) {
            head = current.next;
            return true;
        }

        for(int i = 0; i < position; i++) {
            previous = current;
            current = current.next;
        }

        previous.next = current.next;

        return true;
    }

    public boolean insertToPosition(int position, int data) {
        Node current = head;
        Node newNode = new Node(data);
        if(position > size() || position < 0) {
            System.out.println("Index out of bounds");
            return false;
        }

        if(position == size()) {
            add(data);
            return true;
        }

        if(position == 0) {
            head = newNode;
            head.next = current;
            return true;
        }

        for(int i = 0; i < position-1; i++) {
            current = current.next;
        }

        Node next = current.next;
        current.next = newNode;
        newNode.next = next;
        return true;
    }
}
