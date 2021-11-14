public class Combinations {
  public void backtrack(int first, LinkedList<Integer> curr, int n, int k,List<List<Integer>> output) {
    if (curr.size() == k)
      output.add(new LinkedList(curr));

    for (int i = first; i <= n; i++) {
      // add i into the current combination
      curr.add(i);
      // use next integers to complete the combination
      backtrack(i + 1, curr, n, k, output);
      // backtrack
      curr.removeLast();
    }
  }

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> output = new LinkedList<>();
    backtrack(1, new LinkedList<Integer>(), n, k, output);
    return output;
  }
}