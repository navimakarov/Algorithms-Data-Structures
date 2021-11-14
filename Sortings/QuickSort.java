import java.util.Arrays;
import java.util.Random;
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 9, 8, 0, -1, 7, 6, 100, 1000};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int l, int r) {
        if(l < r) {
            int pivot = partition(arr, l, r);
            sort(arr, l, pivot-1);
            sort(arr, pivot+1, r);
        }
    }

    private static int partition(int[] arr, int l, int r) {
        randomize(arr, l, r);
        int pivot = arr[r];
        int i = l-1;
        for(int j = l; j < r; j++) {
            if(arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, ++i, r);
        return i;
    }

    private static void randomize(int[] arr, int l, int r) {
        Random random = new Random();
        int index = random.nextInt(r-l+1)+l;
        swap(arr, index, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}