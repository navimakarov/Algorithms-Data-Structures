public class SinglyLinkedList {
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

    public static Node findLoop(Node head) {
		Node slow = head.next;
		Node fast = head.next.next;
		while(slow != fast) {
			slow = slow.next;
			fast = fast.next.next;
		}
		slow = head;
		while(slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
  }

  // k starting from 1
  public static void removeKthNodeFromEnd(Node head, int k) {
	Node first = head, second = head;
    int counter = 1;
		while(counter <= k) {
			second = second.next;
			counter++;
		}
		if(second == null) {
			head.value = head.next.value;
			head.next = head.next.next;
		}
		else {
			while(second.next != null) {
				first = first.next;
				second = second.next;
			}
			first.next = first.next.next;
		}
  }
}
