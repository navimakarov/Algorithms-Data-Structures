/**
 * Quick Sort
 */
void sort(int[] arr, int l, int r) {
    if(l < r) {
        int pivot = partition(arr, l, r);
        sort(arr, l, pivot-1);
        sort(arr, pivot+1, r);
    }
}

int partition(int[] arr, int l, int r) {
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

void randomize(int[] arr, int l, int r) {
    Random random = new Random();
    int index = random.nextInt(r-l+1)+l;
    swap(arr, index, r);
}

void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

/**
 * Binary Search Iterative
 */

int search(int[] arr, int key) {
    int l = 0, r = arr.length-1;
    while(l <= r) {
        int mid = l + (r-l)/2;
        if(arr[mid] < key)
           l = mid+1;
        else if(arr[mid] > key)
            r = mid-1;
        else
            return mid;
    }
    return -1;
}