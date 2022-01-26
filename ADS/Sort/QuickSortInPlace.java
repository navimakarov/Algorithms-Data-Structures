public static void sort(int[] data) {
  sort(data, 0, data.length - 1);
}

public static void sort(int[] data, int low, int high) {
  int i = low, j = high;
  int y = data[low + (high-low)/2];

  while (i <= j) {
    while (data[i] < y) i++;
    while (data[j] > y) j--;
    if (i <= j) {
      swap(data, i, j);
      i++;
      j--;
    }
  }

  if (low < j)  sort(data, low, j);
  if (i < high) sort(data, i, high);
}