//Binary Search: Pick a thresh hold use dfs to defect if it can go to R - 1 and C - 1
class Solution {
    public int minimumEffortPath(int[][] heights) { 
        int s=0;
        int e = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        while(s<=e){
            int mid = s + (e - s) / 2;
            boolean [][] visited= new boolean[heights.length][heights[0].length];
            boolean isMin= dfs(0,0,heights[0].length,heights.length,mid,heights,visited);
            if(isMin){
                ans = Math.min(ans,mid);
                 e = mid - 1;
            }else {
                 s = mid + 1;
             }
        }
        return ans;
    }
    
    
   boolean dfs(int i, int j, int n, int m, int maxEffort, int[][] matrix, boolean[][] visited) {
       if (i == m - 1 && j == n - 1) return true;
       visited[i][j] = true;
       boolean p = false;
        if (i + 1 < m && !visited[i + 1][j] && Math.abs(matrix[i+1][j] - matrix[i][j])<=maxEffort) {
            p = p || dfs(i + 1, j, n, m, maxEffort, matrix, visited);
        }
        if (j + 1 < n && !visited[i][j + 1] && Math.abs(matrix[i][j+1] - matrix[i][j])<=maxEffort) {
            p = p ||dfs(i, j + 1, n, m,maxEffort, matrix, visited);
        }
        if (i - 1 >= 0 && !visited[i - 1][j] && Math.abs(matrix[i-1][j] - matrix[i][j])<=maxEffort) {
            p = p ||dfs(i - 1, j, n, m, maxEffort, matrix, visited);
        }
        if (j - 1 >= 0 && !visited[i][j - 1] && Math.abs(matrix[i][j] - matrix[i][j-1])<=maxEffort) {
            p = p ||dfs(i, j - 1, n, m, maxEffort, matrix, visited);
        }
       return p;
    }
}
//My Solution: Djistra traverse all node and effort is the key to sort in pq
class Solution {
    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a ,b) -> a[2] - b[2]);
        int R = heights.length;
        int C = heights[0].length;
        pq.offer(new int[]{0, 0, 0});
        int[][] visited = new int[R][C];
        visited[0][0] = 0;
        for(int[] v : visited){
            Arrays.fill(v, (int)1e6);
        }
        int[] dirs = {0, 1, 0, -1, 0};
        int ans = (int) 1e6;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0] == R - 1 && cur[1] == C - 1){
                ans = Math.min(ans, cur[2]);
            }
            for(int i = 0; i < 4; ++i){
                int nx = cur[0] + dirs[i];
                int ny = cur[1] + dirs[i + 1];
                if(nx < 0 || ny < 0 || nx == R || ny == C)continue;
                
                int effort = Math.abs(heights[cur[0]][cur[1]] - heights[nx][ny]);
                effort = Math.max(cur[2], effort);
                if(visited[nx][ny] <= effort) continue;
                visited[nx][ny] = effort;
                pq.offer(new int[]{nx, ny, effort});
                
            }
        }
        return ans;
    }
}
