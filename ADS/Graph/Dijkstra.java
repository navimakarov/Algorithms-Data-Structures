import java.util.*;


public class Dijkstra {
    public static void main(String[] args) {
        List<List<Node>> adj_list = new ArrayList<>();
        for(int i = 0; i < 4; i++)
            adj_list.add(new ArrayList<Node>());
        
        adj_list.get(0).add(new Node(1, 1));
        adj_list.get(0).add(new Node(3, 10));
        adj_list.get(0).add(new Node(2, 5));

        adj_list.get(1).add(new Node(0, 1));
        adj_list.get(1).add(new Node(2, 2));
        adj_list.get(1).add(new Node(3, 6));

        adj_list.get(2).add(new Node(1, 2));
        adj_list.get(2).add(new Node(3, 1));
        adj_list.get(2).add(new Node(0, 5));

        adj_list.get(3).add(new Node(2, 1));
        adj_list.get(3).add(new Node(0, 10));
        adj_list.get(3).add(new Node(1, 6));

        System.out.println(dijkstra(adj_list, 3, 1));
    }
    public static int dijkstra(List<List<Node>> adj_list, int source, int dest) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> seen = new HashSet<>();
        int[] dist = new int[adj_list.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;
        pq.offer(new Node(source, 0));
        while(!pq.isEmpty()) {
            Node e = pq.poll();
            seen.add(e.dst);
            update_neighbours(adj_list, e, dist, pq, seen);
        }
        return dist[dest];
    }

    private static void update_neighbours(List<List<Node>> adj_list, Node curr, int[] dist, PriorityQueue<Node> pq, Set<Integer> seen) {
        for(Node adj : adj_list.get(curr.dst)) {
            if(!seen.contains(adj.dst)) {
                if(dist[curr.dst] + adj.weight < dist[adj.dst]) {
                    dist[adj.dst] = dist[curr.dst] + adj.weight;
                }
                pq.offer(adj);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int dst, weight;

        public Node(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }

        public int compareTo(Node e2) {
            return Integer.compare(this.weight, e2.weight);
        }
    }
}