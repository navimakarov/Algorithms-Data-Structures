/**
 * Dijkstra's Algorithm
 */
Set<Integer> discovered;
int[] dist;
PriorityQueue<GraphNode> pq;
public void dijkstra(int src) {
    dist = new int[size];
    for(int i = 0; i < size; i++) {
        dist[i] = Integer.MAX_VALUE;
    }
    discovered = new HashSet<>();
    pq = new PriorityQueue<GraphNode>(size, new GraphNode());
    pq.add(new GraphNode(src, 0));
    dist[0] = 0;
    while(discovered.size() != size) {
        int u = pq.remove().vertex;
        discovered.add(u);
        graph_adjacentNodes(u);
    }
    for(int i = 0; i < size; i++) {
        System.out.println("Distance: " + dist[i]);
    }
}

private void graph_adjacentNodes(int vertex) {
    int edge_distance, new_distance;
    for(GraphNode node: adj_list.get(vertex)) {
        if(!discovered.contains(node.vertex)) {
            edge_distance = node.weight;
            new_distance = dist[vertex] + edge_distance;
            if (new_distance < dist[node.vertex])
                dist[node.vertex] = new_distance;
            pq.add(new GraphNode(
                node.vertex, dist[node.vertex]));
        }
    }
}

/**
 * Detecting Cycle in Undirected graph
 * BFS Approach
 */
public boolean isCyclic() {
    int[] parent = new int[size];
    boolean[] visited = new boolean[size];
    Arrays.fill(parent, -1);
    Arrays.fill(visited, false);
    Queue<Integer> q = new LinkedList<>();
    visited[0] = true;
    q.add(0);
    while(!q.isEmpty()) {
        int u = q.poll();
        for(int i = 0; i < adj_list.get(u).size(); i++) {
            int v = adj_list.get(u).get(i).vertex;
            if(!visited[v]){
                visited[v] = true;
                q.add(v);
                parent[v] = u;
            }
            else if(parent[u] != v)
                return  true;
        }
    }
    return false;
}