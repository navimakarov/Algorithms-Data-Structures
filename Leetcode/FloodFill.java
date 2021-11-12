public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[0].length; j++) {
                if(image[i][j] != color)
                    visited[i][j] = true;
                else
                    visited[i][j] = false;
            }
        }
        dfs(image, visited, sr, sc, newColor);
        
        return image;
        
    }
    
    private void dfs(int[][] image, boolean[][] visited, int x, int y, int newColor) {
        visited[x][y] = true;
        image[x][y] = newColor;
        if(x - 1 >= 0 && !visited[x-1][y]) {
            dfs(image, visited, x-1, y, newColor);
        }
        if(x + 1 < image.length && !visited[x+1][y]) {
            dfs(image, visited, x+1, y, newColor);
        }
        if(y - 1 >= 0 && !visited[x][y-1]) {
            dfs(image, visited, x, y-1, newColor);
        }
        if(y + 1 < image[0].length && !visited[x][y+1]) {
            dfs(image, visited, x, y+1, newColor);
        }
    }
}