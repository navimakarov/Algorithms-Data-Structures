//Kruskal's min spanning tree algorithm
public void kruskalAlgorithm() {
    ArrayList<Edge> edges = new ArrayList<>();
    for(int i = 0; i < size; i++) {
      for(GraphNode node : adj_list.get(i)) {
        Edge e1 = new Edge(i, node.vertex, node.weight);
        Edge e2 = new Edge(node.vertex, i, node.weight);
        if(!edges.contains(e1) && !edges.contains(e2))
            edges.add(e1);
      }
    }
    edges.sort(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.weight > o2.weight)
                return 1;
            else if(o1.weight < o2.weight)
                return -1;
            return 0;
        }
    });

    Graph res = new Graph(size, false, true);
    int count = 0;
    while(count < size-1) {
        Edge edge = edges.get(0);
        edges.remove(0);

        res.addEdge(edge.src, edge.dst, edge.weight);
        if(res.isCyclic())
            res.removeEdge(edge.src, edge.dst);
        else {
            count++;
        }
    }
    for(int i = 0; i < res.adj_list.size(); i++) {
      for(GraphNode node: res.adj_list.get(i))
        System.out.println(i + "-" + node.vertex + 
                          "\t" + node.weight);
    }
}
//Detecting Cycle in Directed graph (DFS Approach)
private boolean isCyclicUtil(int i, boolean[] visited, 
                                    boolean[] recStack){
    if (recStack[i])
        return true;
    if (visited[i])
        return false;         
    visited[i] = true;
    recStack[i] = true;
    List<Integer> children = adj.get(i);
    for (Integer c: children)
        if (isCyclicUtil(c, visited, recStack))
            return true;
    recStack[i] = false;
    return false;
}
private boolean isCyclic(){
    boolean[] visited = new boolean[V];
    boolean[] recStack = new boolean[V];
    for (int i = 0; i < V; i++)
        if (isCyclicUtil(i, visited, recStack))
            return true;
    return false;
}