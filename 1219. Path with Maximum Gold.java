_____________________________________________________________Best Solution DFS_____________________________________________________________
class Solution {
 // only dfs the end of path or start of path( one of its adj is not zero or its in corner)
    public int getMaximumGold(int[][] grid) {
	    // initialize 2D array with boundary filled with zero cells for simpler code
        int [][] matrix = new int[grid.length + 2][grid[0].length + 2];
        for(int i = 0; i< grid.length; i++) {
            for(int j = 0; j< grid[0].length; j++) {
                matrix[i + 1][j + 1] = grid[i][j];
            }
        }
        int max = 0;
		// run dfs in the cases described above
        // only dfs the end of path or start of path( one of its adj is zero)
        for(int i = 1; i<= grid.length; i++) {
            for(int j = 1; j<= grid[0].length; j++) {
                int zeroCount = 0;
                if (matrix[i - 1][j] == 0) zeroCount++; // top
                if (matrix[i][j - 1] == 0) zeroCount++; // left
                if (zeroCount == 2) { // For top left corner
                    max = Math.max(max, dfs(matrix, i, j));
                    continue;
                }
                // one of top or left is not zero, and it will be only way out
                if (matrix[i + 1][j] == 0) zeroCount++;// bot
                if (matrix[i][j + 1] == 0) zeroCount++;// right
                // only one way out as to one of top or left
                if (zeroCount == 3) {  // Surrounded by 3 cells
                    max = Math.max(max, dfs(matrix, i, j));
                }
            }
        }
        return max;
    }
    // typical DFS.   from bottom to up 
    int directions[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int dfs(int[][] matrix, int y, int x) {
        if (y < 0 || y >= matrix.length || x < 0 || x >= matrix[0].length || matrix[y][x] == 0) return 0;
        int max = 0;
        int current = matrix[y][x];
        matrix[y][x] = 0;
        for(int i = 0; i < 4; i++) {
            max = Math.max(max, dfs(matrix, y + directions[i][0], x + directions[i][1]));
        }
        matrix[y][x] = current;
        return max + current; 
    }
}
____________________________________________________________My Solution DFS________________________________________________________________
class Solution {
    int[] dirs = {0, -1, 0, 1, 0};
    int ans = 0, r, c;
    public int getMaximumGold(int[][] grid) {
        r = grid.length;
        c = grid[0].length;
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j){
                if(grid[i][j] != 0){
                    dfs(grid, i, j, 0);
                }
            }
        }
        return ans;
    }
    private void dfs(int[][] map, int x, int y, int val){
        if(x < 0 || y < 0 || x == r || y == c || map[x][y] <= 0){
            if(ans < val){
                ans = val;
            }
            return;
        }
        int temp = map[x][y];
        map[x][y] = -1;
        for(int i = 0; i < 4; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            dfs(map, nx, ny, val + temp);
        }
        map[x][y] = temp;
    }
}
