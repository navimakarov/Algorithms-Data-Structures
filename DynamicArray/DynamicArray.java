public class DynamicArray {

    private int[] arr;
    private int capacity;
    private int current;

    public DynamicArray() {
        capacity = 1;
        arr = new int[capacity];
        current = 0;
    }

    public void push(int data) {
        if (current == capacity) {
            int[] temp = new int[2 * capacity];

            for (int i = 0; i < capacity; i++)
                temp[i] = arr[i];

            capacity *= 2;
            arr = temp;
        }

        arr[current] = data;
        current++;
    }

    public int get(int index) {
        if (index < current)
            return arr[index];

        return -1;
    }

    public void pop() {
        current--;
    }

    public int size() {
        return current;
    }

    public int getcapacity() {
        return capacity;
    }

    public void print() {
        for (int i = 0; i < current; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}