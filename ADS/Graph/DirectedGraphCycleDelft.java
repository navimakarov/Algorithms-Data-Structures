class Solution {
	public static boolean inCycle(List<Map<Integer,Integer>> graph, int vertex) {
        return helper(graph,vertex,vertex,new HashSet<>());
    }

    public static boolean helper(List<Map<Integer,Integer>> graph, int search, int current, Set<Integer> known) {
        known.add(current);
        for(int next : graph.get(current).keySet()) {
            if(next == search) {
                return true;
            } else if(!known.contains(next)) {
                if(helper(graph,search,next,known)) {
                    return true;
                }
            }
        }
        return false;
    }
}