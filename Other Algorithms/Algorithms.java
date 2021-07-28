public class Algorithms {
    public static int binarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == x)
                return mid;

            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            return binarySearch(arr, mid + 1, r, x);
        }

        return -1;
    }

    public static int binarySearchIteratively(int[] sortedArray, int key, int low, int high) {
        int index = -1;

        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (sortedArray[mid] < key) {
                low = mid + 1;
            }
            else if (sortedArray[mid] > key) {
                high = mid - 1;
            }
            else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static int pow_num(int a, int b) {
        if (a == 0) {
            return 0;
        }
        int ans = 1;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans *= a;
            }
            b >>= 1;
            a *= a;
        }
        return ans;
    }
}
