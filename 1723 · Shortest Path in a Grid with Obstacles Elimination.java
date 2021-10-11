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
