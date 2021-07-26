public class CircularLinkedList {
    private class Node {
        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head = null, tail = null;

    public void add(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
    }

    public boolean remove(int data) {
        Node currentNode = head;
        if (head == null) {
            System.out.println("List is empty");
            return false;
        }

        do {
            Node nextNode = currentNode.next;
            if (nextNode.data == data) {
                if (head == tail) { // the list has only one single element
                    head = null;
                    tail = null;
                }
                else {
                    currentNode.next = nextNode.next;
                    if (head == nextNode) { //we're deleting the head
                        head = head.next;
                    }
                    if (tail == nextNode) { //we're deleting the tail
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);

        System.out.println("List is empty");
        return false;
    }

    public void display() {
        Node current = head;
        if(current == null) {
            System.out.println("Empty list");
        }

        while(current != null) {
            System.out.println(current.data + " ");
            current = current.next;
            if(current == head)
                break;
        }
        System.out.println();
    }
}
