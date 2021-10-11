//Best Solution improved by me: Doing check in for loop to reduce a level of recursion
public class Solution {
    /**
     * @param grid: a list of list
     * @param k: an integer
     * @return: Return the minimum number of steps to walk
     */
    
    int ans = Integer.MAX_VALUE;
    int[] dirs = {0, 1, 0, -1, 0};
    int R;
    int C;
    public int shortestPath(int[][] grid, int k) {
        R = grid.length;
        C = grid[0].length;
        if(R * C == 1) return 0;

        grid[0][0] = -1;
        find(grid, 0, 0, k, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void find(int[][] grid, int sx, int sy, int limit, int steps){
        int nx = 0;
        int ny = 0;
        int temp;
        for(int i = 0; i < 4; ++i){
            nx = sx + dirs[i];
            ny = sy + dirs[i + 1];
            if(nx < 0 || nx == R || ny < 0 || ny == C || grid[nx][ny] == -1) continue;
            if(nx == R - 1 && ny == C - 1){
                ans = Math.min(ans, steps + 1);
                return;
            }
            temp = grid[nx][ny];
            if(limit - temp < 0 || ans == R + C - 2 || ans < steps) continue;

            grid[nx][ny] = -1;
            find(grid, nx, ny, limit - temp, steps + 1);
            grid[nx][ny] = temp;
        }
    }
}
//Best Solution: DFS, finding stops when the path is Row + Col - 2 or limit is reached
public class Solution {
    public int n, m, mmin;
    public int[] x_dir = new int[]{1, 0, -1, 0};
    public int[] y_dir = new int[]{0, 1, 0, -1};

    Boolean isValid(int x, int y){
        if(x >= 0 && x < m && y >= 0 && y < n){
            return true;
        }
        return false;
    }
    void dfs(int[][] grid,int x,int y,int k,int count){
        if(!isValid(x, y) || grid[x][y]==-1){
            return;
        }

        if((k == 0 && grid[x][y] == 1) || mmin == m + n - 2){
            return;
        }

        if(x == m - 1 && y == n - 1){
            if(count < mmin)
                mmin = count;
            return;
        }
        
        if(grid[x][y] == 1)
            k--;

        int temp = grid[x][y];
        grid[x][y] = -1;
        for(int d = 0;d < 4;d++){
            int tempx = x_dir[d] + x;
            int tempy = y_dir[d] + y;
            dfs(grid, tempx, tempy,k, count + 1);
        }
        grid[x][y] = temp;
    }
    public int shortestPath(int[][] grid, int k) {
        // write your code here
        int count=0;
        mmin = 0x3f3f3f3f; 
        m = grid.length;
        n = grid[0].length;
        
        dfs(grid, 0, 0, k, count);

        return mmin == 0x3f3f3f3f ? -1 : mmin;
    }
}
// My Solution: BFS only append when arrive at grid[i][j] with different eliminate with in limit
public class Solution {
    public int shortestPath(int[][] grid, int k) {
        int[] dirs = {0, 1, 0, -1, 0};
        int R = grid.length;
        int C = grid[0].length;
        int[][] move = new int[R][C];
        for(int[] m : move){
            Arrays.fill(m, Integer.MAX_VALUE);
        }
        move[0][0] = 0;
        Queue<int[]> q = new LinkedList<int[]>();
        int steps = 0;
        q.offer(new int[]{0, 0});
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int[] curPos = q.poll();
                if(curPos[0] == R - 1 && curPos[1] == C - 1){
                    return steps;
                }
                int nx = 0;
                int ny = 0;
                for(int i = 0; i < 4; ++i){
                    nx = curPos[0] + dirs[i];
                    ny = curPos[1] + dirs[i + 1];
                    if(nx < 0 || nx == R || ny < 0 || ny == C ||
                    move[curPos[0]][curPos[1]] + grid[nx][ny] > k ||
                    move[curPos[0]][curPos[1]] + grid[nx][ny] >= move[nx][ny]) continue;
                    
                    move[nx][ny] = move[curPos[0]][curPos[1]] + grid[nx][ny];
                    q.offer(new int[]{nx, ny});
                }
            }
            ++steps;
        }
        return -1;
    }
}
