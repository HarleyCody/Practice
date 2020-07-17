_______________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // flood island if not touch boarder, its a closed Island
    // dfs change status in for(d= 0; d < 4; ++d) will be faster
    int R, C;
    int[] dirs =  {0, 1, 0 ,-1, 0};
    public int closedIsland(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        int ans = 0;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 0){
                    grid[r][c] = 1;
                    if(flood(grid, r, c)){
                        ++ans;
                    }
                }
            }
        }
        
        return ans;
    }
    
    private boolean flood(int[][] grid, int x, int y){
        boolean rlt = true;
        if(x == R - 1 || x == 0 || y == C - 1 || y ==0){
            rlt = false;
        }
        
        for(int d = 0; d < 4; ++d){
            int nx = x + dirs[d];
            int ny = y + dirs[d + 1];
            
            if(nx == R || ny == C || nx < 0 || ny < 0 || grid[nx][ny] != 0){
                continue;
            }
            grid[nx][ny] = 1;
            rlt &= flood(grid, nx, ny);
        }
        return rlt;
    }
}
