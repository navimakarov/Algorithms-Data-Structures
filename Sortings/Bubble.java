import java.util.*;

public class Bubble {
    public static int[] bubbleSort(int[] array) {
    if(array.length == 0)
			return array;
		
		boolean isSorted = false;
		int counter = 0;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i < array.length - 1 - counter; i++) {
				if(array[i] > array[i+1]) {
					array[i] += array[i+1];
					array[i+1] = array[i] - array[i+1];
					array[i] -= array[i+1];
					isSorted = false;
				}
			}
			counter++;
		}
    return array;
  }
}