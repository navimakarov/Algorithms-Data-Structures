public class Stack {
    private static final int MAX = 1000;
    private int top;
    private int[] array = new int[MAX]; // Maximum size of Stack

    public boolean isEmpty() {
        return (top < 0);
    }

    public Stack() {
        top = -1;
    }

    public boolean push(int x) {
        if (top >= (MAX - 1)) {
            System.out.println("Stack Overflow");
            return false;
        }
        else {
            array[++top] = x;
            System.out.println(x + " pushed into stack");
            return true;
        }
    }

    public int pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            return array[top--];
        }
    }

    public int peek() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            return array[top];
        }
    }
}
