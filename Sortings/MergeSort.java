import java.util.Arrays;
public class MergeSort {
    public static void main(String[] args) {
        int[] toSort = new int[] {5, 8, 0, -1, 2, 9, 10, 20, -8, 6};
        sort(toSort, 0, toSort.length-1);
        System.out.println(Arrays.toString(toSort));
    }

    public static void sort(int[] arr, int l, int r) {
        if(l < r) {
            int mid = l + (r-l)/2;
            sort(arr, l, mid);
            sort(arr, mid+1, r);

            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        int n1 = mid - l + 1;
        int n2 = r - mid;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i = 0; i < n1; i++) {
            L[i] = arr[l+i];
        }
        for(int i = 0; i < n2; i++) {
            R[i] = arr[mid+1+i];
        }

        int i = 0, j = 0, k = l;
        while(i < n1 && j < n2) {
            if(L[i] < R[j]) {
                arr[k++] = L[i++];
            }
            else {
                arr[k++] = R[j++];
            }
        }

        while(i < n1) {
            arr[k++] = L[i++];
        }

        while(j < n2) {
            arr[k++] = R[j++];
        }
    }
}