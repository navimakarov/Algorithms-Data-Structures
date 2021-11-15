public class LongestIncreasingSubsequence {
    int lis(int arr[], int n){
        int lis[] = new int[n];
 
        for (int i = 0; i < n; i++)
            lis[i] = 1;
 
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
 
        return max(lis);
    }
 
}