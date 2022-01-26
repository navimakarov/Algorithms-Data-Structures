public class RotateImage {
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for(int i = 0; i < size; i++) {
            for(int j = i; j < size; j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        
        for(int i = 0; i < size; i++) {
            reverse(matrix[i]);
        }
    }
    
    public void reverse(int[] array) {
        int start = 0;
        int end = array.length-1;
        while(start < end) {
            int tmp = array[start];
            array[start++] = array[end];
            array[end--] = tmp;
        }
    }
}