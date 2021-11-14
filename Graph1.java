/**
 * Kahn's Algorithm (Topological Sort)
 */
void kahnTopologicalSort() {
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


/**
 * Loop detection in DIRECTED GRAPH
 */
boolean containsLoop() {
    boolean visited[] = new boolean[V];
    Arrays.fill(visited, false);
    for(int i = 0; i < V; i++) {
        if(hasLoop(i, Arrays.copyOf(visited, visited.length)))
            return true;
    }
    return false;
}

boolean hasLoop(int node, boolean[] visited) {
    if(visited[node]) {
        return true;
    }
    visited[node] = true;
    
    boolean loop = false;
    for(int adj : adj_list.get(node)) {
        loop = loop || hasLoop(adj, visited);
    }
    visited[node] = false;
    return loop;
}