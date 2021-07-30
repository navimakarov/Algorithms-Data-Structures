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

    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while(current != null) {
            next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }
        tail = head;
        head = prev;
    }

    public Node shiftNode(Node head, int k) {
        Node first = head;
        Node second = head;
        k %= size(head);
        if(k < 0) {
            k += size(head);
        }
        for(int i = 0; i < k; i++) {
            second = second.next;
        }
        if(second == null) {
            return head;
        }
        else {
            while(second.next != null) {
                first = first.next;
                second = second.next;
            }
            if(first.next == null)
                return head;

            Node newHead = first.next;
            first.next = null;
            second.next = head;
            return newHead;
        }
    }

    public Node nodeSwap(Node head) {
        if(head == null || head.next == null)
            return head;
        Node prev = null;
        Node next = head.next;
        Node res = next;
        while(next != null) {
            if(prev != null) {
                prev.next = next;
            }
            head.next = next.next;
            next.next = head;

            prev = head;
            head = prev.next;
            if(head == null)
                break;
            next = head.next;
        }
        return res;
    }

    public int size(Node head) {
        int size = 0;
        while(head != null) {
            head = head.next;
            size++;
        }
        return size;
    }
}
