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
