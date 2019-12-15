_________________________________________________________Best Solution___________________________________________________________
// visited limits the path is unique.
// possibilities is record by varaible instead of hashMap
// dfs by using for is slower than by dfs explicitly. cause dirs will retrive value from global variable
class Solution {
    int walkover = ans = 0;
    int rowLen, colLen;
    int[] start;
    public int uniquePathsIII(int[][] grid) {
        rowLen = grid.length;
        colLen = grid[0].length;
        start = new int[2];
        for(int i = 0; i < rowLen; ++i){
            for(int j = 0; j < colLen; ++j){
                if(grid[i][j] == 0) ++walkover;
                else if(grid[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        dfs(grid, start[0], start[1], walkover);
        return ans;
    }
    private void dfs(int[][]grid, int x, int y, int walkover){
        if(x < 0 || y < 0 || x >= rowLen || y >= colLen || grid[x][y] == -1) return;
        if(grid[x][y] == 2){
            // further step to 2; so -1 
            if(walkover == -1)++ans;
            return;
        }
        grid[x][y] = -1;
        dfs(grid, x + 1, y, walkover - 1);
        dfs(grid, x - 1, y, walkover - 1);
        dfs(grid, x, y + 1, walkover - 1);
        dfs(grid, x, y - 1, walkover - 1);
        
        grid[x][y] = 0;
    }
}
____________________________________Improved DFS Solution(varaible instead of hashset)_________________________________________
// visited limits the path is unique.
// possibilities is record by varaible instead of hashMap
// dfs
class Solution {
    int walkover = 0;
    int rowLen, colLen;
    int[] start;
    int dirs[] = new int[]{0, -1, 0, 1, 0};
    boolean[][] visited;
    int ans = 0;
    public int uniquePathsIII(int[][] grid) {
        rowLen = grid.length;
        colLen = grid[0].length;
        start = new int[2];
        visited = new boolean[rowLen][colLen];
        for(int i = 0; i < rowLen; ++i){
            for(int j = 0; j < colLen; ++j){
                if(grid[i][j] != -1) ++walkover;
                if(grid[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        --walkover;
        dfs(grid, start[0], start[1]);
        return ans;
    }
    private void dfs(int[][]grid, int x, int y){
        if(walkover == 0 && grid[x][y] == 2){
            ++ans;
            return;
        }
        visited[x][y] = true;
        --walkover;
        for(int i = 0; i < 4; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            if(nx < 0 || ny < 0 || nx >= rowLen || ny >= colLen || 
               grid[nx][ny] == -1 || visited[nx][ny]) continue;
            dfs(grid, nx, ny);
        }
        ++walkover;
        visited[x][y] = false;
    }
}
_________________________________________________________My Solution___________________________________________________________
// recorder every path(String) by hashSet;
// path is recorded by its direction for every step
// dfs
class Solution {
    int walkover = 0;
    int rowLen, colLen;
    int[] start, end;
    int dirs[] = new int[]{0, -1, 0, 1, 0};
    HashSet<String> recorder = new HashSet();
    boolean[][] visited;
    public int uniquePathsIII(int[][] grid) {
        rowLen = grid.length;
        colLen = grid[0].length;
        start = new int[2];
        end = new int[2];
        visited = new boolean[rowLen][colLen];
        for(int i = 0; i < rowLen; ++i){
            for(int j = 0; j < colLen; ++j){
                if(grid[i][j] == 0) ++walkover;
                if(grid[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }
                if(grid[i][j] == 2){
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        dfs(grid, start[0], start[1], new StringBuilder());
        return recorder.size();
    }
    private void dfs(int[][]grid, int x, int y, StringBuilder path){
        if(path.length() == walkover + 1 && x == end[0] && y == end[1]){
            recorder.add(path.toString());
            return;
        }
        visited[x][y] = true;
        StringBuilder temp = new StringBuilder(path);
        for(int i = 0; i < 4; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            if(nx < 0 || ny < 0 || nx >= rowLen || ny >= colLen || 
               grid[nx][ny] == -1 || visited[nx][ny]) continue;
            path.append((char)i);
            dfs(grid, nx, ny, path);
            path = new StringBuilder(temp);
        }
        visited[x][y] = false;
    }
}
