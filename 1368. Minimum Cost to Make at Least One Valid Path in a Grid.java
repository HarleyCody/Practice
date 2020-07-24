____________________________________________________________________________Best Solution_________________________________________________________________________________
class Solution {
    // bfs add all node with same cost and start to find all node with cost + 1;
    // dfs find all node with same cost
    // only label cost once
    // O(mn);
    int INF = (int) 1e9;
    int[][] DIR = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    public int minCost(int[][] grid) {
        m = grid.length; n = grid[0].length;
        int cost = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], INF);
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
            }
        }
        return dp[m - 1][n - 1];
    }

    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) return;
        dp[r][c] = cost;
        bfs.offer(new int[]{r, c}); // add to try change direction later
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }
}
______________________________________________________________________________My Solution_________________________________________________________________________________
class Solution {
    // dijkstra
    // update cost from 0, 0 to current node
    public int minCost(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[] cost = new int[R * C];
        for(int[] row : cost){
            Arrays.fill(row, 200);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((x, y) -> cost[x[0]][x[1]] - cost[y[0]][y[1]]);
        boolean[][] visited = new boolean[R][C];
        cost[0][0] = 0;
        pq.offer(new int[]{0, 0});
        
        int ans = 200;
        while(!pq.isEmpty()){
            int[] c = pq.poll();
            if(c[0] == R - 1 && c[1] == C - 1){
                ans = Math.min(ans, cost[c[0]][c[1]]);
            }
            
            for(int i = 1; i <= 4; ++i){
                int[] n = next(c[0], c[1], i);
                if(n[0] < 0 || n[0] == R || n[1] == C || n[1] < 0 ||
                   cost[c[0]][c[1]] >= cost[n[0]][n[1]]){
                    continue;
                }
                visited[n[0]][n[1]] = true;
                cost[n[0]][n[1]] = i == grid[c[0]][c[1]] ? cost[c[0]][c[1]] : cost[c[0]][c[1]] + 1;
                pq.offer(new int[]{n[0], n[1]});
            }
        }
        return ans;
    }
    private int[] next(int x, int y, int val){
        if(val == 1){
            ++y;
        }else if(val == 2){
            --y;
        }else if(val == 3){
            ++x;
        }else{
            --x;
        }
        return new int[]{x, y};
    }
}
