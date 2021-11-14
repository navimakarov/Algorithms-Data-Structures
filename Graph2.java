/**
 * Dijkstra's Algorithm
 */
Set<Integer> discovered;
int[] dist;
PriorityQueue<GraphNode> pq;

void dijkstra(int src) {
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

void graph_adjacentNodes(int vertex) {
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
 * Loop detection in UNDIRECTED GRAPH
 */
public boolean hasCycle(Graph graph, int v, boolean[] visited, int parent){
    visited[v] = true;
    for (int w : graph.adjList.get(v)){
        if (!visited[w]){
            if (hasCycle(graph, w, visited, v)) {
                return true;
            }
        }
        else if (w != parent) {
            return true;
        }
    }

    return false;
}