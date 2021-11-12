import java.util.*;
public class Loop {
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj_list = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            adj_list.add(new ArrayList<>());
        }

        adj_list.get(0).add(1);
        adj_list.get(2).add(1);
        adj_list.get(2).add(3);
        adj_list.get(3).add(4);
        adj_list.get(4).add(0);
        //adj_list.get(4).add(2);

        Loop loop = new Loop(adj_list);
        System.out.println(loop.containsLoop());
    }

    List<List<Integer>> adj_list;
    int V;
    public Loop(List<List<Integer>> adj_list) {
        this.adj_list = adj_list;
        this.V = adj_list.size();
    }

    public boolean containsLoop() {
        boolean visited[] = new boolean[V];
        Arrays.fill(visited, false);
        for(int i = 0; i < V; i++) {
            if(hasLoop(i, Arrays.copyOf(visited, visited.length)))
                return true;
        }
        return false;
    }

    private boolean hasLoop(int node, boolean[] visited) {
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

    public boolean DFS(Graph graph, int v, boolean[] discovered, int parent){
        // mark the current node as discovered
        discovered[v] = true;
 
        // do for every edge (v, w)
        for (int w: graph.adjList.get(v))
        {
            // if `w` is not discovered
            if (!discovered[w])
            {
                if (DFS(graph, w, discovered, v)) {
                    return true;
                }
            }
 
            // if `w` is discovered, and `w` is not a parent
            else if (w != parent)
            {
                // we found a back-edge (cycle)
                return true;
            }
        }
 
        // No back-edges were found in the graph
        return false;
    }
}