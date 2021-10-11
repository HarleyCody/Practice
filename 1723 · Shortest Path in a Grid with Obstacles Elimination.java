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
