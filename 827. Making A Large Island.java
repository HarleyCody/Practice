________________________________________________________________Best Solution____________________________________________________________
class Solution {
    //improve, donot use extrax space to store root
    //root is indexed by self-increasing number so do not need to calculate x * R + y;
    public int largestIsland(int[][] grid) {
        int[] sizes = new int[2502];
        int cur = 2;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                dfs(i, j, grid, cur++, sizes);
            }
        }
        int res = 0;
        for(int i = 0; i < 2502; i++){
            if (sizes[i] > res) {
                res = sizes[i];
            }
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    int u = search(i - 1, j, grid, sizes);
                    int d = search(i + 1, j, grid, sizes);
                    int l = search(i, j - 1, grid, sizes);
                    int r = search(i, j + 1, grid, sizes);
                    int largest = sizes[u] + 1;
                    if(d != u){
                        largest += sizes[d];
                    }
                    if(l != u && l != d){
                        largest += sizes[l];
                    }
                    if(r != l && r != u && r != d){
                        largest += sizes[r];
                    }
                    if(largest > res){
                        res = largest;
                    }
                }
            }
        }
        return res;
    }
    //union, connect grid with root;
    private void dfs(int i, int j, int[][] grid, int cur, int[] sizes){
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = cur;
            sizes[cur]++;
            dfs(i - 1, j, grid, cur, sizes);
            dfs(i + 1, j, grid, cur, sizes);
            dfs(i, j - 1, grid, cur, sizes);
            dfs(i, j + 1, grid, cur, sizes);
        }
    }
    // return root;
    private int search(int i, int j, int[][] grid, int[] sizes){
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] > 1) {
            return grid[i][j];
        }
        return 0;
    }
}
_________________________________________________________________My Solution_____________________________________________________________
class Solution {
    // Union all land in island to a point
    // find land by idx;
    // record area of root point;
    // find a ocean try to get max ans by connect neighbors of ocean
    int R, C;
    int[] root, area;
    int[][] map;
    boolean[][] visited;
    public int largestIsland(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        int len = R * C;
        map = grid;
        
        root = new int[len];
        area = new int[len];
        visited = new boolean[len][len];
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 1 && !visited[r][c]){
                    int idx = r * C + c;
                    root[idx] = idx;
                    buildIsland(r, c, r * C + c);
                }
            }
        }
        
        int ans = 1;
        boolean hasOcean = false;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 0){
                    hasOcean = true;
                    int curArea = 1;
                    boolean[] been = new boolean[len];
                    int og;
                    if(1 <= r && grid[r - 1][c] == 1){
                        og = root[(r - 1) * C + c];
                        curArea += area[og];
                        been[og] = true;
                    }
                    if(r < R - 1 && grid[r + 1][c] == 1){
                        og = root[(r + 1) * C + c];
                        curArea += been[og] ? 0 : area[og];
                        been[og] = true;
                    }
                    if(1 <= c && grid[r][c - 1] == 1){
                        og = root[r * C + c - 1];
                        curArea += been[og] ? 0 : area[og];
                        been[og] = true;
                    }
                    if(c < R - 1 && grid[r][c + 1] == 1){
                        og = root[r * C + c + 1];
                        curArea += been[og] ? 0 : area[og];
                        been[og] = true;
                    }
                    ans = Math.max(curArea, ans);
                }
            }
        }
        return hasOcean ? ans : R * C;
    }
    
    private void buildIsland(int x, int y, int root){
        if(map[x][y] == 0 || visited[x][y]){
            return;
        }
        union(x * C + y, root);
        visited[x][y] = true;
        if(1 <= x){
            buildIsland(x - 1, y, root);
        }
        if(x < R - 1){
            buildIsland(x + 1, y, root);
        }
        if(1 <= y){
            buildIsland(x, y - 1, root);
        }
        if(y < R - 1){
            buildIsland(x, y + 1, root);
        }
    }
    
    private void union(int x, int y){
        root[x] = y;
        ++area[y];
    }
    
    private int find(int x){
        return root[x];
    }
}
