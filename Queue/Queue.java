public class Queue {
    private static int front, rear, capacity;
    private static int[] queue;

    public Queue(int c)
    {
        front = rear = 0;
        capacity = c;
        queue = new int[capacity];
    }

    public void enqueue(int data) {
        if (capacity == rear) {
            System.out.println("Queue is full");
        }
        else {
            queue[rear] = data;
            rear++;
        }
    }

    public void dequeue() {
        if (front == rear) {
            System.out.println("Queue is empty");
        }
        else {
            for (int i = 0; i < rear - 1; i++) {
                queue[i] = queue[i + 1];
            }
            if (rear < capacity)
                queue[rear] = 0;

            rear--;
        }
    }

    public void poll() {
        if (front == rear) {
            System.out.println("Queue is Empty");
        }
        else {
            System.out.println("Front Element is: " + queue[front]);
        }
    }
}