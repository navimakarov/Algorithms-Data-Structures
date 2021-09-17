/**
 * Prim's Algorithm
 */
public void primAlgorithm() {
    boolean[] visited = new boolean[size];
    int[] key = new int[size];
    int[] parent = new int[size];

    for(int i = 0; i < size; i++) {
        visited[i] = false;
        key[i] = Integer.MAX_VALUE;
    }
    key[0] = 0;
    parent[0] = -1;
    for(int count = 0; count < size-1; count++) {
        int u = minKey(key, visited);
        visited[u] = true;

        for(int i = 0; i < size; i++) {
            if(matrix[u][i] != 0 && !visited[i] && 
               matrix[u][i] < key[i]) {
                key[i] = matrix[u][i];
                parent[i] = u;
            }
        }
    }
    printMST(parent);
}

private void printMST(int[] parent) {
    for(int i = 1; i < size; i++) {
      System.out.println(parent[i] + "-" + i + 
                        "\t" + matrix[parent[i]][i]);
    }
}

private int minKey(int[] key, boolean[] visited) {
    int value = Integer.MAX_VALUE, index=-1;
    for(int i = 0; i < key.length; i++) {
        if(key[i] < value && !visited[i]){
            value = key[i];
            index = i;
        }
    }
    return index;
}
