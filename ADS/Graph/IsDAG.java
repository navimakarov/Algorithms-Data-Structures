class Solution {
	public static boolean isDAG(List<Map<Integer,Integer>> graph) {
        for(int i = 0; i < graph.size(); i++) {
            if(helper(graph,i,i,new HashSet<>())) {
                return false;
            }
        }
        return true;
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