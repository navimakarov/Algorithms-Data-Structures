
public class QueueAsLinkedList {
    static class QNode {
        int key;
        QNode next;

        public QNode(int key)
        {
            this.key = key;
            next = null;
        }
    }

    QNode front = null, rear = null;

    public void enqueue(int key) {
        QNode temp = new QNode(key);

        if (rear == null) {
            front = rear = temp;
            return;
        }

        rear.next = temp;
        rear = temp;
    }

    public void dequeue() {
        if (front == null)
            return;

        front = front.next;

        if (front == null)
            rear = null;
    }

    public int peek() {
        if(front == null) {
            System.out.println("Queue is empty");
            return 0;
        }
        return front.key;
    }
}
  