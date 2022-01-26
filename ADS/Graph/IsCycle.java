package weblab;

import java.util.*;

class Solution
{
	public static boolean inCycle(List<Map<Integer,Integer>> graph, int vertex) {
        return helper(graph, vertex, vertex, new HashSet<Integer>());
    }

    public static boolean helper(List<Map<Integer, Integer>> graph, int start, int curr, Set<Integer> seen) {
        seen.add(curr);
        for(int next : graph.get(curr).keySet()) {
            if(next == start)
                return true;
            if(!seen.contains(next)) {
                if(helper(graph, start, next, seen))
                    return true;
            }
        }
        return false;
    }
}
