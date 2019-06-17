class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        int wide = grid.length, depth = grid[0].length;
        int area, max = 0;
        for(int i = 0; i < wide; ++i){
            for(int j = 0; j < depth; ++j){
                // search from new point, reset area
                area = 0;
                if(grid[i][j] == 0) continue;
                else{
                    area += calculate(grid, wide, depth, i, j);
                    grid[i][j] = 0;
                    max = Math.max(area, max);
                }
            }
        }
        return max;
    }
    // calculate area of island in dfs.
    private int calculate(int[][] grid, int wide, int depth, int row, int col){
        int area = 0;
        if(row < 0 || col < 0 || row == wide || col == depth || grid[row][col] == 0) return area;
        // add current area and change current island to zero, so will not caculate repeatedly.
        grid[row][col] = 0;
        area = 1;
        area += calculate(grid, wide, depth, row + 1, col);
        area += calculate(grid, wide, depth, row - 1, col);
        area += calculate(grid, wide, depth, row, col + 1);
        area += calculate(grid, wide, depth, row, col - 1);
        return area;
    }
}
