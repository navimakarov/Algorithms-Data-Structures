package com.makarov.graphs;

import java.util.*;

public class Graph {
    ArrayList<ArrayList<GraphNode>> adj_list;
    int[][] matrix;
    int size;
    boolean isOriented;
    boolean isWeighted;
    private boolean[] visited;

    Graph(int size, boolean isOriented, boolean isWeighted) {
        this.size = size;
        this.isOriented = isOriented;
        this.isWeighted = isWeighted;

        adj_list = new ArrayList<>(size);
        matrix = new int[size][size];
        initMatrix();
        initList();

        visited = new boolean[size];
    }

    // -------------------------------------------------------------------------
    /**
     * DFS
     * Time complexity - O(V^2) - matrix, O(V+E) - adj list
     */
    // -------------------------------------------------------------------------
    public void dfs() {
        for(int i = 0; i < size; i++) {
            visited[i] = false;
        }
        for(int i = 0; i < size; i++) {
            if(!visited[i]) {
                dfs_rec(i);
                // dfs_stack(i);
                System.out.println();
            }
        }
    }

    private void dfs_rec(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for(GraphNode node : adj_list.get(vertex)) {
            if(!visited[node.vertex])
                dfs_rec(node.vertex);
        }
    }

    private void dfs_stack(int vertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);

        while(!stack.isEmpty()) {
            int v = stack.pop();

            if(visited[v]) {
                continue;
            }

            visited[v] = true;
            System.out.print(v + " ");

            for(GraphNode node : adj_list.get(v)) {
                if(!visited[node.vertex])
                    stack.push(node.vertex);
            }
        }
    }
    // -------------------------------------------------------------------------
    /**
     * BFS
     * Time complexity - O(V^2) - matrix, O(V+E) - adj list
     */
    // -------------------------------------------------------------------------
    public void bfs() {
        for(int i = 0; i < size; i++) {
            visited[i] = false;
        }
        for(int i = 0; i < size; i++) {
            if(!visited[i]) {
                bfsUtil(i);
                System.out.println();
            }
        }
    }

    private void bfsUtil(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);

        while(!queue.isEmpty()) {
            int v = queue.poll();

            if(visited[v])
                continue;

            visited[v] = true;
            System.out.print(v + " ");

            for(GraphNode node : adj_list.get(v)) {
                if(!visited[node.vertex])
                    queue.add(node.vertex);
            }
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Topological Sort
     * Time complexity = DFS = O(V+E) or O(V^2) for matrix
     */
    // -------------------------------------------------------------------------
    Stack<Integer> stack;
    public void topologicalSort() {
        stack = new Stack<>();
        for(int i = 0; i < size; i++) {
            visited[i] = false;
        }
        for(int i = 0; i < size; i++) {
            if(!visited[i]) {
                topologicalSortUtil(i);
            }
        }
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }

    private void topologicalSortUtil(int vertex) {
        visited[vertex] = true;

        for(GraphNode node : adj_list.get(vertex)) {
            if(!visited[node.vertex])
                topologicalSortUtil(node.vertex);
        }

        stack.push(vertex);
    }
    // -------------------------------------------------------------------------
    /**
     * Kahn's Algorithm for Topological sort
     * Time complexity - O(V+E)
     */
    // -------------------------------------------------------------------------
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
    // -------------------------------------------------------------------------
    /**
     * Prim's Algorithm
     * Time complexity - O(V^2)
     */
    // -------------------------------------------------------------------------
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
                if(matrix[u][i] != 0 && !visited[i] && matrix[u][i] < key[i]) {
                    key[i] = matrix[u][i];
                    parent[i] = u;
                }
            }
        }
        printMST(parent);
    }

    private void printMST(int[] parent) {
        for(int i = 1; i < size; i++) {
            System.out.println(parent[i] + "-" + i + "\t" + matrix[parent[i]][i]);
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

    // -------------------------------------------------------------------------
    /**
     * Kruskal's Algorithm
     * Time complexity O(E*log(V))
     */
    // -------------------------------------------------------------------------
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
                System.out.println(i + "-" + node.vertex + "\t" + node.weight);
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Detecting Cycle in UnDirected graph
     * BFS approach
     */
    // -------------------------------------------------------------------------
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

    // -------------------------------------------------------------------------
    /**
     * Detecting Cycle in Directed graph
     * DFS approach
     */
    // -------------------------------------------------------------------------

    private boolean isCyclicUtil(int i, boolean[] visited,
                                      boolean[] recStack){

        // Mark the current node as visited and
        // part of recursion stack
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

    private boolean isCyclic()
    {
         
        // Mark all the vertices as not visited and
        // not part of recursion stack
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
         
         
        // Call the recursive helper function to
        // detect cycle in different DFS trees
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
 
        return false;
    }
 

    // -------------------------------------------------------------------------
    /**
     * Dijkstra
     */
    // -------------------------------------------------------------------------
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
                pq.add(new GraphNode(node.vertex, dist[node.vertex]));
            }
        }
    }

    /**
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

    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    private void initMatrix() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    private void initList() {
        for(int i = 0; i < size; i++) {
            adj_list.add(new ArrayList<>());
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        matrix[vertex1][vertex2] = 1;
        adj_list.get(vertex1).add(new GraphNode(vertex2));
        if(!isOriented) {
            matrix[vertex2][vertex1] = 1;
            adj_list.get(vertex2).add(new GraphNode(vertex1));
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        matrix[vertex1][vertex2] = weight;
        adj_list.get(vertex1).add(new GraphNode(vertex2, weight));
        if(!isOriented) {
            adj_list.get(vertex2).add(new GraphNode(vertex1, weight));
            matrix[vertex2][vertex1] = weight;
        }
    }

    public void removeEdge(int vertex1, int vertex2) {
        matrix[vertex1][vertex2] = 0;
        GraphNode toRemove = null;
        for(GraphNode node : adj_list.get(vertex1)) {
            if(node.vertex == vertex2) {
                adj_list.get(vertex1).remove(node);
                break;
            }
        }
        if(!isOriented) {
            for(GraphNode node : adj_list.get(vertex2)) {
                if(node.vertex == vertex1) {
                    adj_list.get(vertex2).remove(node);
                    break;
                }
            }
            matrix[vertex2][vertex1] = 0;
        }
    }

    public void printMatrix() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printList() {
        for(int i = 0; i < size; i++) {
            System.out.println(adj_list.get(i));
        }
    }

    static class GraphNode implements Comparator<GraphNode> {
        int weight;
        int vertex;

        GraphNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        GraphNode(int vertex) {
            this.vertex = vertex;
            this.weight = -1;
        }

        GraphNode() {

        }

        @Override
        public String toString() {
            return "Vertex: " + vertex + " Weight: " + weight;
        }

        @Override
        public int compare(GraphNode o1, GraphNode o2) {
            return Integer.compare(o1.weight, o2.weight);
        }
    }

    static class Edge {
        int src, dst, weight;

        Edge(int src, int dst, int weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return src == edge.src && dst == edge.dst && weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(src, dst, weight);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6, false, true);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 4, 3);
        graph.addEdge(0, 5, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 3);
        graph.dijkstra(0);
        //graph.addEdge(5, 2);
        //graph.addEdge(5, 0);
        //graph.addEdge(4, 0);
        //graph.addEdge(4, 1);
        //graph.addEdge(2, 3);
        //graph.addEdge(3, 1);
        /*
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 10);
        graph.addEdge(0, 3, 10);

        graph.addEdge(1, 4, 20);
        graph.addEdge(2, 6, 20);
        graph.addEdge(3, 7, 20);

        graph.addEdge(4, 5, 30);
        graph.addEdge(6, 9, 30);

        graph.addEdge(5, 8, 40);

        graph.addEdge(10, 11, 100);
        graph.addEdge(10, 12, 100);
        graph.addEdge(10, 13, 100);
        graph.addEdge(11, 14, 200);
        graph.addEdge(14, 15, 300);

         */

        /*

        System.out.println("List: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.printList();
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        System.out.println("Matrix: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.printMatrix();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("DFS: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.dfs();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("BFS: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.bfs();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("Topological sort: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.topologicalSort();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("Kahn's Topological sort: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.kahnTopologicalSort();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("Prim's MST: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.primAlgorithm();
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("Kruskal's MST: ");
        System.out.println("@@@@@@@@@@@@@@@@@@@");
        graph.kruskalAlgorithm();
        System.out.println("@@@@@@@@@@@@@@@@@@@");


         */
    }

}
