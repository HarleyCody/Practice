//Best Solution: As there is only one direction indicated by each cell. So do not need dfs, dfs will only be a simple recursive here.
//Use for loop to detect the buttom
class Solution {
    public int[] findBall(int[][] grid) {
        int rowLength = grid.length, colLength = grid[0].length;
        
        int[] result = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            result[i] = start(grid, i, rowLength, colLength);
        }
        return result;
    }
    
    public int start(int[][] grid, int col, int rowLength, int colLength) {
        for (int row = 0; row < grid.length; row++) {
            int nextCol = col + grid[row][col];
            if (nextCol < 0 || nextCol == colLength || grid[row][col] * grid[row][nextCol] == -1){
                return -1;
            }
            col = nextCol;
        }
        return col;
    }
}
//My Solution: dfs find the path and memoization cache the results in every possible nodes
class Solution {
    int[][] mem;
    public int[] findBall(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        mem = new int[row][col];
        int[] ans = new int[col];
        
        for(int c = 0; c < col; ++c){
            ans[c] = fall(0, c, row, col, grid);
        }
        return ans;
    }
    
    private int fall(int cx, int cy, int r, int c, int[][] map){
        if(cx == r) return cy;
        if(mem[cx][cy] != 0) return mem[cx][cy];
        int nx = cx, ny = cy + map[cx][cy];
        
        if(ny == c || ny == -1 || map[nx][ny] * map[cx][cy] == -1){
            return mem[cx][cy] = -1;
        }
        
        nx += 1;
        return mem[cx][cy] = fall(nx, ny, r, c, map);
    }
}
