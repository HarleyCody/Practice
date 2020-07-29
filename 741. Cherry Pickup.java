______________________________________________________________________________Best Solution________________________________________________________________________________
// DFS + Mem improved
class Solution {
    //improve
    // compress dimension to compress status, status only need x1,y1,y2. x2 can be calcualted by x1 + y1 - y2
    // return MIN_VALUE instead of -1 as -1 need to worried about +=next > 0; MIN will always be negative in this question
    // prunning moving down and right in same row situation, as x1 down x2 right same as x2 down x1 right
    // when they overlap they must be at same point, so do not need visited[][] to record point
    // do not use class variable to pass grid, pass grid as map in function parameter
    
    int[][][] mem;
    int len;
    public int cherryPickup(int[][] grid) {
        len = grid.length;
        mem = new int[len][len][len];
        
        int ans = pick(grid, 0,0,0);
        return Math.max(ans , 0);
    }
    
    private int pick(int[][] map, int x1, int y1, int y2){
        int x2 = x1 + y1 - y2;
        if(x1 == len || x2 == len || y1 == len || y2 == len || map[x1][y1] == -1 || map[x2][y2] == -1){
            return Integer.MIN_VALUE;
        }
        if(x1 == len - 1 && y1 == len - 1){
            return map[x1][y1];
        }
        if(mem[x1][y1][y2] != 0){
            return mem[x1][y1][y2];
        }
        
        int ans = x1 == x2 ? map[x1][y1] : map[x1][y1] + map[x2][y2];
        
        int next = Math.max(pick(map, x1 + 1, y1, y2), pick(map, x1, y1 + 1, y2));
        next = Math.max(next, pick(map, x1, y1 + 1, y2 + 1));
        if(x1 != x2){
            // prunning this point can also be acheived by move x2 down move y1 to right if they are start at same point
            next = Math.max(next, pick(map, x1 + 1, y1, y2 + 1));
        }
        ans += next;
        return mem[x1][y1][y2] = ans;
    }
}
______________________________________________________________________________Dp Solution________________________________________________________________________________
class Solution {
    // compressed status into 2D, i row of x1 p col of x2 as they both has same lenth(ie, row + col) the other pair of coordiates can be calcualted
    public int cherryPickup(int[][] grid) {
        // M count of steps
        int N = grid.length, M = (N << 1) - 1;
        // dp is max at point N, N;
        int[][] dp = new int[N][N];
        dp[0][0] = grid[0][0];

        for (int n = 1; n < M; n++) {
            for (int i = N - 1; i >= 0; i--) {
                for (int p = N - 1; p >= 0; p--) {
                    // j is col of i, q is row of p;
                    // two point has same steps
                    
                    int j = n - i, q = n - p;
                    
                    if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;
                        continue;
                    }
                    
                    // both from up in N - 1 steps 
                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
                    // both from left in N - 1 steps 
                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
                    // one of them from up. the other one from left in N - 1 steps 
                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
                    
                    // sum from two points as dp[i][p] only contains data from previous step
                    // dp[i][p] must be updated by previous step as dp[i][p] >= 0 (reachable), so after max above, dp only represents max in previous step 
                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
                 }
             }
        }

        return Math.max(dp[N - 1][N - 1], 0);
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class Solution {
    //dfs + mem
    //record status by x1, y1 and x2, y2 as two points only one of them can pick cherry
    //find two path from 0,0 to len - 1, len - 1
    boolean[][] visited;
    int[][][][] mem;
    int[][] map;
    int len;
    public int cherryPickup(int[][] grid) {
        len = grid.length;
        visited = new boolean[len][len];
        mem = new int[len][len][len][len];
        map = grid;
        
        int ans = pick(0,0,0,0);
        return ans == -1 ? 0 : ans;
    }
    
    private int pick(int x1, int y1, int x2, int y2){
        if(map[x1][y1] == -1 || map[x2][y2] == -1){
            return -1;
        }
        if(x1 == len - 1 && y1 == len - 1 && x2 == len - 1 && y2 == len - 1){
            System.out.println("reach end");
            return map[x1][y1];
        }
        if(mem[x1][y1][x2][y2] != 0){
            return mem[x1][y1][x2][y2];
        }
        
        int ans = (visited[x1][y1] ? 0 : map[x1][y1]) + (visited[x2][y2] ? 0 : map[x2][y2]);
        if(x1 == x2 && y1 == y2){
            ans >>= 1;
        }
        visited[x1][y1] = true;
        visited[x2][y2] = true;
        int next = -1;
        if(x1 < len - 1 && x2 < len - 1){
            next = pick(x1 + 1, y1, x2 + 1, y2);
        }
        if(y1 < len - 1 && x2 < len - 1){
            next = Math.max(next, pick(x1, y1 + 1, x2 + 1, y2));
        }
        if(x1 < len - 1 && y2 < len - 1){
            next = Math.max(next, pick(x1 + 1, y1, x2, y2 + 1));
        }
        if(y1 < len - 1 && y2 < len - 1){
            next = Math.max(next, pick(x1, y1 + 1, x2, y2 + 1));
        }
        visited[x1][y1] = false;
        visited[x2][y2] = false;
        mem[x1][y1][x2][y2] = next == -1 ? -1 : ans + next;
        return mem[x1][y1][x2][y2];
    }
}
