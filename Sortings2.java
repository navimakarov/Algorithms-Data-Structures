/**
 * Merge Sort
 */
void sort(int[] arr, int l, int r) {
    if(l < r) {
        int mid = l + (r-l)/2;
        sort(arr, l, mid);
        sort(arr, mid+1, r);

        merge(arr, l, mid, r);
    }
}

void merge(int[] arr, int l, int mid, int r) {
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

/**
 * Binary Search Reccursive
 */

 int search(int[] arr, int l, int r, int key) {
     if(l <= r) {
        int mid = l+(r-l)/2;
        if(arr[mid] < key)
            return search(arr, mid+1, r, key);
        else if(arr[mid] > key)
            return search(arr, l, mid-1, key);
        else
            return mid;
     }
     return -1;
 }