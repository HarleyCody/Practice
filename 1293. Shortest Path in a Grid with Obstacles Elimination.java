_______________________________________________________________________________Best Solution________________________________________________________________________________
// DFS purnning by stop at point that can directly go to end. calculate distance
// directly go : with break chance k, if end.x - cur.x + end - y - cur.y < k cur can go to end no matter what
class Solution {
    // dfs
    // do not need to find exatct (r - 1, m - 1) find first point that can reach with k which is larger than manhattan distance from the point to end;
    // dis will be step + manhattan distance
    int minimum;
    int m ,n;
    int[][] track;
    public int shortestPath(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        minimum = (m*n)+1;
        track = new int[m][n];
        travel(0,0,0,k,grid);
        return (minimum == (m*n)+1)? -1: minimum;
    }
    public void travel(int row , int col,int step, int k,int[][] grid)
    {
        if(col<0 || row<0 || row>= m|| col>=n || k<0 ||grid[row][col] == 2)
            return;
        if(k <= track[row][col])
            return;
        // find reachable point near end point
        if(((m - 1 - row) + (n - col -1) - 1) <= k   && grid[row][col] == 0){
            minimum = Math.min(minimum,step +(m - row-1) + (n - col-1));
            return;
        }
        int curr = grid[row][col];
        
        if(curr == 1)
            k-=1;
        
        grid[row][col] = 2;
        travel(row,col+1,step+1,k,grid);
        travel(row+1,col,step+1,k,grid);
        travel(row,col-1,step+1,k,grid);
        travel(row-1,col,step+1,k,grid);
        
        if(curr == 1)
            k +=1;
        
        grid[row][col] = curr;
        track[row][col] = k;
    }
}
_______________________________________________________________________________Best BFS Solution________________________________________________________________________________
class Solution {
    //bfs, bfs has assured min distance. so do not need to update
    //as cost is 1 so dijkstra will regression to bfs problem
    int[] dirs = {1, 0, -1, 0, 1};
    public int shortestPath(int[][] grid, int k) {
        int R = grid.length, C = grid[0].length;
        // int[4] x, y, k, and steps
        boolean[][][] visited = new boolean[R][C][k + 1];
        int[][][] minDis = new int[R][C][k + 1];
        
        Queue<int[]> pq = new LinkedList();
        pq.offer(new int[]{0, 0, 0, 0});
        
        int steps = 0;
        while(!pq.isEmpty()){
            int size = pq.size();
            for(int s = 0; s < size; ++s){
                int[] cur = pq.poll();
                if(cur[0] == R - 1 && cur[1] == C - 1){
                    return cur[3];
                }
                if(cur[2] > k){
                    continue;
                }
                for(int i = 0; i < 4; ++i){
                    int nx = cur[0] + dirs[i];
                    int ny = cur[1] + dirs[i + 1];
                    
                    if(nx < 0 || ny < 0 || nx == R || ny == C){
                        continue;
                    }
                    
                    int nk = cur[2] + grid[nx][ny];
                    int ns = cur[3] + 1;
                    if(nk > k || visited[nx][ny][nk]){
                        continue;
                    }
                    visited[nx][ny][nk] = true;
                    pq.offer(new int[]{nx, ny, nk, ns});
                }
            }
            ++steps;
        }
        return -1;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class Solution {
    //dijkstra
    int[] dirs = {1, 0, -1, 0, 1};
    public int shortestPath(int[][] grid, int k) {
        int R = grid.length, C = grid[0].length;
        // int[4] x, y, k, and steps
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x, y) -> x[3] - y[3]);
        boolean[][][] visited = new boolean[R][C][k + 1];
        int[][][] minDis = new int[R][C][k + 1];
        
        for(int i = 0; i <= k; ++i){
            pq.offer(new int[]{0, 0, i, 0});
            visited[0][0][k] = true;
        }
        
        int steps = 0;
        while(!pq.isEmpty()){
            int size = pq.size();
            for(int s = 0; s < size; ++s){
                int[] cur = pq.poll();
                if(cur[0] == R - 1 && cur[1] == C - 1){
                    return cur[3];
                }
                if(cur[2] > k){
                    continue;
                }
                for(int i = 0; i < 4; ++i){
                    int nx = cur[0] + dirs[i];
                    int ny = cur[1] + dirs[i + 1];
                    
                    if(nx < 0 || ny < 0 || nx == R || ny == C){
                        continue;
                    }
                    
                    int nk = grid[nx][ny] == 1 ? cur[2] + 1 : cur[2];
                    int ns = cur[3] + 1;
                    if(nk > k || (visited[nx][ny][nk] && ns >= minDis[nx][ny][nk])){
                        continue;
                    }
                    
                    minDis[nx][ny][nk] = cur[3] + 1;
                    visited[nx][ny][nk] = true;
                    pq.offer(new int[]{nx, ny, nk, ns});
                }
            }
            ++steps;
        }
        return -1;
    }
}
