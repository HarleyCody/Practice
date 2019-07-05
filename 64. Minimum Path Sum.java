_____________________________________________________Best Solution(dp + recur)_________________________________________________
class Solution {
// use dp to reduce calcualtion, record min in dp[i][j]
    public int traverse(int[][] grid, int x, int y, int[][] dp) {
        if (x == grid.length || y == grid[0].length) {
            return Integer.MAX_VALUE;
        }
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return grid[x][y];
        }
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        return dp[x][y] = Math.min(traverse(grid, x + 1, y, dp), traverse(grid, x, y + 1, dp)) + grid[x][y];
    }
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return traverse(grid, 0, 0, dp);
    }
}
___________________________________________________My Solution(dp)_____________________________________________________________
class Solution {
    public int minPathSum(int[][] grid) {
        
        if(grid.length == 0) return 0;
        int rLen = grid.length, cLen = grid[0].length;
        // pave last row
        for(int c = cLen - 2; 0 <= c ; --c){
            grid[rLen - 1][c] += grid[rLen - 1][c + 1];
        }
        if(grid.length == 1) return grid[0][0];
        // pave last column
        for(int r = rLen - 2; 0 <= r ; --r){
            grid[r][cLen - 1] += grid[r + 1][cLen - 1];
        }
        for(int i = rLen - 2; i >= 0; --i){
            for(int j = cLen - 2; j >= 0; --j){
                // only can move right or down. so current cell is decided by min(left,down)
                grid[i][j] += Math.min(grid[i + 1][j], grid[i][j + 1]);
            }
        }
        return grid[0][0];
    }
}
___________________________________________________My Solution(recur)___________________________________________________________
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid.length == 0) return 0;
        return getMin(grid, 0, 0);
    }
    // calcualte subpath recursively.
    private int getMin(int[][] grid, int r, int c){
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length){
            return Integer.MAX_VALUE;
        }
        int curMin = grid[r][c];
        if(r == grid.length - 1 && c == grid[0].length - 1){
            return curMin;
        }
        curMin += Math.min(getMin(grid, r + 1, c), getMin(grid,r, c + 1)); 
        return curMin;
    }
}
