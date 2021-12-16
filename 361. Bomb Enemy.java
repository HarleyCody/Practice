
//Best Solution: Striaght forward, calculate the row count and col count for every '0' cell and get the max. Record row count and col count. Onlt hit wall or a new row will need to count again
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int maxCount = 0, rowHits = 0;
        int[] colHits = new int[cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(col == 0 || grid[row][col - 1] == 'W'){
                    rowHits = 0;
                    for(int k = col; k < cols; k++){
                        if(grid[row][k] == 'W') break;
                        if(grid[row][k] == 'E') rowHits += 1;
                    }
                }
                if(row == 0 || grid[row - 1][col] == 'W'){
                    colHits[col] = 0;
                    for(int k = row; k < rows; k++){
                        if(grid[k][col] == 'W') break;
                        if(grid[k][col] == 'E') colHits[col] += 1;
                    }
                }
                if(grid[row][col] == '0'){
                    maxCount = Math.max(maxCount, rowHits + colHits[col]);
                }
            }
        }
        return maxCount;
    }
}
//My Solution: Scan from left top and right bot to accumulate
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        
        int[][] count = new int[row][col];
        int[][] above = new int[row][col];
        int[][] left = new int[row][col];
        int[][] below = new int[row][col];
        int[][] right = new int[row][col];
        int ans = 0;
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(grid[r][c] == 'E') {
                    above[r][c] += 1;
                    left[r][c] += 1;
                }
                
                if(grid[r][c] != 'W'){
                    if(0 < r) above[r][c] += above[r - 1][c];
                    if(0 < c) left[r][c] += left[r][c - 1];
                    count[r][c] += above[r][c] + left[r][c];
                    if(grid[r][c] == 'E')--count[r][c];
                }
                
                
                int rr = row - r - 1;
                int bc = col - c - 1;
                if(grid[rr][bc] == 'E') {
                    below[rr][bc] += 1;
                    right[rr][bc] += 1;
                }
                if(grid[rr][bc] != 'W'){
                    if(rr < row - 1) below[rr][bc] += below[rr + 1][bc];
                    if(bc < col - 1) right[rr][bc] += right[rr][bc + 1];
                    count[rr][bc] += below[rr][bc] + right[rr][bc];
                    if(grid[rr][bc] == 'E')count[rr][bc] -= 2;
                }
                
                if(grid[r][c] == '0') ans = Math.max(count[r][c], ans);
                if(grid[rr][bc] == '0') ans = Math.max(count[rr][bc], ans);
            }
        }
        return ans;
    }
}
