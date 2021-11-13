//Best Solution: Same idea from top left and bot right
//Improve: use a single for loop to handle the top and bot layer
//and only handle the left right boarder in the nested loop
class Solution {
    public int maxDistance(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int max = 201;
        int[][] dis = new int[R][C];
        dis[0][0] = grid[0][0] == 1? 0 : max;
        for(int c = 1; c < C; ++c){
            dis[0][c] = grid[0][c] == 1 ? 0 : dis[0][c - 1] + 1; 
        }
        //Top left
        for(int r = 1; r < R; ++r){
            dis[r][0] = grid[r][0] == 1? 0 : dis[r - 1][0] + 1;
            for(int c = 1; c < C; ++c){
                if(grid[r][c] == 1) continue;
                dis[r][c] = Math.min(dis[r - 1][c], dis[r][c - 1]) + 1;
            }
        }
        
        int ans = dis[R - 1][C - 1];
        for(int c = C - 2; 0 <= c; --c){
            dis[R - 1][c] = Math.min(dis[R - 1][c], 1 + dis[R - 1][c + 1]);
            ans = Math.max(ans, dis[R - 1][c]);
        }
        //Bot right
        for(int r = R - 2; 0 <= r; --r){
            dis[r][C - 1] = Math.min(dis[r][C - 1], dis[r + 1][C - 1] + 1);
            ans = Math.max(ans, dis[r][C - 1]);
            for(int c = C - 2; 0 <= c; --c){
                dis[r][c] = Math.min(dis[r][c], dis[r][c + 1] + 1);
                dis[r][c] = Math.min(dis[r][c], dis[r + 1][c] + 1);
                ans = Math.max(ans, dis[r][c]);
            }
        }

        return ans == 0 || ans >= max? -1 : ans;
    }
}
//My Better Solution: DP only from bot right and top left, so it covers four direction top left bot right//My Solution: DP scan from four cornors and update it is distance
class Solution {
    public int maxDistance(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][] dis = new int[R][C];
        
        int max = 201;
        int prev = 0;
        int v1 = 0;
        int v2 = 0;

        //Top left
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 1) continue;
                dis[r][c] = max;
                v1 = c == 0 ? max : dis[r][c - 1];
                v2 = r == 0 ? max : dis[r - 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = prev;
            }
        }
        //Bot right
        int ans = 0;
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
                v1 = c == C - 1 ? max : dis[r][c + 1];
                v2 = r == R - 1 ? max : dis[r + 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = Math.min(dis[r][c], prev);
                ans = Math.max(ans, dis[r][c]);
            }
        }        
        return ans == 0 || ans == max? -1 : ans;
    }
}
//My Solution: DP scan from four cornors and update it is distance
class Solution {
    public int maxDistance(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][] dis = new int[R][C];
        
        int max = 201;
        int prev = 0;
        int v1 = 0;
        int v2 = 0;

        //Top left
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 1) continue;
                dis[r][c] = max;
                v2 = r == 0 ? max : dis[r - 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = prev;
            }
        }
        //Bot right
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
                if(grid[r][c] == 1) continue;
                v1 = c == C - 1 ? max : dis[r][c + 1];
                v2 = r == R - 1 ? max : dis[r + 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = Math.min(dis[r][c], prev);
            }
        }
        //Top right
        for(int r = 0; r < R; ++r){
             for(int c = C - 1; 0 <= c; --c){
                if(grid[r][c] == 1) continue;
                v1 = c == C - 1 ? max : dis[r][c + 1];
                v2 = r == 0 ? max : dis[r - 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = Math.min(dis[r][c], prev);
            }
        }
        //Bot Left
        int ans = 0;
        for(int r = R - 1; 0 <= r; --r){
            for(int c = 0; c < C; ++c){
                if(grid[r][c] == 1) continue;
                v1 = c == 0 ? max : dis[r][c - 1];
                v2 = r == R - 1 ? max : dis[r + 1][c];
                prev = Math.min(v1, v2);
                prev = prev == max? prev : prev + 1;
                dis[r][c] = Math.min(dis[r][c], prev);
                ans = Math.max(ans, dis[r][c]);
            }
        }
        
        return ans == 0 || ans == max? -1 : ans;
    }
}
