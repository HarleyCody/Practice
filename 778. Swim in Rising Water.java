___________________________________________________________________Binary Search & DFS___________________________________________________
// improved use bs to set limit, dfs searching if can pass with limited time
class Solution {
    // key visited keeps unchanged, as only one of dfs pass just return pass
    int[][] map;
    int R;
    public int swimInWater(int[][] grid) {
        map = grid;
        R = grid.length;
    
        int l = 0, r = R * R - 1;
        while(l < r){
            int m = (l + r) / 2;
            
            if(canPass(new boolean[R][R], 0, 0, 0, m)){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return r;
    }
    
    private boolean canPass(boolean[][] visited, int x, int y, int cur, int limit){
        cur = Math.max(map[x][y], cur);
        if(cur > limit || visited[x][y]){
            return false;
        }
        
        if(x == R - 1 && y == R - 1){
            return true;
        }
        
        visited[x][y] = true;
        if(0 <= x - 1 && 0 <= y && y < R && canPass(visited, x - 1, y, cur, limit)){
            return true;
        }
        
        if(x + 1 < R && 0 <= y && y < R && canPass(visited, x + 1, y, cur, limit)){
            return true;
        }
        
        if(0 <= x && x < R && 0 <= y - 1 && canPass(visited, x, y - 1, cur, limit)){
            return true;
        }
        
        if(0 <= x && x < R && y + 1< R && canPass(visited, x, y + 1, cur, limit)){
            return true;
        }
        visited[x][y] = false;
        return false;
    }
}

______________________________________________________________________DFS TLE______________________________________________________________
class Solution {
    int[] dirs = {0, 1, 0, -1, 0};
    int ans, R;
    boolean[][] visited;
    public int swimInWater(int[][] grid) {
        R = grid.length;
        ans = R * R;
        visited = new boolean[R][R];
        times(grid, 0, 0, grid[0][0]);
        return ans;
    }
    
    private void times(int[][]grid, int x, int y, int curTime){
        if(x == R - 1 && y == R - 1){
            ans = Math.min(curTime, ans);
            return;
        }
        visited[x][y] = true;
        curTime = Math.max(curTime, grid[x][y]);
        for(int i = 0; i < 4; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            if(nx < 0 || ny < 0 || nx == R || ny == R || visited[nx][ny] || grid[nx][ny] >= ans){
                continue;
            }
            times(grid, nx, ny, curTime);
        }
        visited[x][y] = false;
    }
}
