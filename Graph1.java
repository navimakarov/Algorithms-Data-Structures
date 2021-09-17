/**
 * Kahn's Algorithm (Topological Sort)
 */
public void kahnTopologicalSort() {
    int[] dependencies = new int[size];
    for(int i = 0; i < size; i++) {
        dependencies[i] = 0;
    }
    for(int i = 0; i < size; i++) {
        for(GraphNode node : adj_list.get(i))
            dependencies[node.vertex]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 0; i < size; i++) {
        if(dependencies[i] == 0) {
            queue.add(i);
        }
    }
    ArrayList<Integer> res = new ArrayList<>();
    int cnt = 0;
    while(!queue.isEmpty()) {
        int vertex = queue.poll();
        for(GraphNode node : adj_list.get(vertex)){
            if(--dependencies[node.vertex] == 0)
                queue.add(node.vertex);
        }
        res.add(vertex);
        cnt++;
    }
    if(cnt != size) {
        System.out.println("Loop detected");
        return;
    }
    System.out.println(res);
}
/*
 * Floyd Algorithm
 * Shortest path between all vertexes 
*/
public void floyd() {
    int dist[][] = new int[size][size];
    for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            dist[i][j] = matrix[i][j];
        }
    }

    for(int k = 0; k < size; k++) {
      for(int i = 0; i < size; i++) {
        for(int j = 0; j < size; j++) {
            if(dist[i][k] + dist [k][j] < dist[i][j])
                dist[i][j] = dist[i][k] + dist [k][j];
        }
      }
    }
}
