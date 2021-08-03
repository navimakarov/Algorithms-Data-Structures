package com.makarov.graphs;

import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    static class GraphNode {
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

        @Override
        public String toString() {
            return "Vertex: " + vertex + " Weight: " + weight;
        }
    }
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

    public static void main(String[] args) {
        Graph graph = new Graph(6, true, false);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
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
    }
}
