class Solution {
    // BFS , recursion to fill
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // record visited node;
    int[][] visited;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length == 0) return image;
        // flood node
        int og = image[sr][sc];
        // start flood
        visited = new int[image.length][image[0].length];
        color(image, sr, sc, newColor, og);
        return image;
    }
    public void color(int[][] image, int sr, int sc, int newColor, int og){
        // out of boundary, visited or current node is not og color. do not flood (terminate);
        if(sr < 0 || sr == image.length || sc < 0 || sc == image[0].length 
           || visited[sr][sc] == 1 || image[sr][sc] != og) return;
        image[sr][sc] = newColor;
        // change dirs to flood;
        for(int i = 0 ; i < 4; ++i){
            visited[sr][sc] = 1;
            int nR = sr + dirs[i][0];
            int nC = sc + dirs[i][1];
            color(image, nR, nC, newColor, og);
            visited[sr][sc] = 0;
        }
    }
}
