// Prim's Algorithm in Java

import java.util.Arrays;

class PGraph {

  public void Prim(int G[][], int V) {

    int INF = Integer.MAX_VALUE;

    boolean[] selected = new boolean[V];
    Arrays.fill(selected, false);

    int no_edge = 0; 
    selected[0] = true;

    System.out.println("Edge : Weight");

    while (no_edge < V - 1) {
      int min = INF;
      int x = 0; 
      int y = 0; 

      for (int i = 0; i < V; i++) {
        if (selected[i] == true) {
          for (int j = 0; j < V; j++) {
            // not in selected and there is an edge
            if (!selected[j] && G[i][j] != 0) {
              if (min > G[i][j]) {
                min = G[i][j];
                x = i;
                y = j;
              }
            }
          }
        }
      }
      System.out.println(x + " - " + y + " :  " + G[x][y]);
      selected[y] = true;
      no_edge++;
    }
  }
}