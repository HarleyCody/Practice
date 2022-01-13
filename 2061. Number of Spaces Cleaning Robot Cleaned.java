//Best Solution: use room for visited array, 0, 1 for object 2, 3, 4, 5 for directions
//Best Solution: use room for visited array, 0, 1 for object 2, 3, 4, 5 for directions
class Solution {
    int[][] g;
    int[][] d = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int numberOfCleanRooms(int[][] room) {
        this.g = room;
        return helper(0, 0, 0);
    }


    int helper(int x, int y, int curDir) {

        if (!isValidCell(x, y) || g[x][y] == 1) {
            x -= d[curDir % 4][0];
            y -= d[curDir % 4][1];
            curDir = (++curDir) % 4;
        }
		//	g[][] = 2,3,4,5 -> 4 directions, save the memory of a visited array
        if (curDir + 2 == g[x][y]) return 0;

        if (g[x][y] == 0) {
            g[x][y] = curDir + 2;
            return 1 + helper(x + d[curDir][0], y + d[curDir][1],curDir);
        }
        return helper(x + d[curDir][0], y + d[curDir][1], curDir);
    }


    boolean isValidCell(int x, int y) {
        int row = g.length;
        int col = g[0].length;
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }
}

/*Row and Col > 0
dfs to sweep the floor, record the status of at each floor, if at current floor with current status is not visited then move forward
*/
//My Solution: dfs to go every possible floor and sweep it is not visited. Record the status by bit
class Solution {
    int row;
    int col;
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    int ans = 0;
    int[][] visited;
    public int numberOfCleanRooms(int[][] room) {
        row = room.length;
        col = room[0].length;
        visited = new int[row][col];
        sweepFloor(room, 0, 0, 0);
        
        return ans;
    }
    
    private void sweepFloor(int[][] room, int x, int y, int dIdx){
        if((visited[x][y] & (1 << dIdx)) > 0) return;
        int nx = x;
        int ny = y;
        while(0 <= nx && nx < row && 0 <= ny && ny < col && room[nx][ny] == 0){
            if(visited[nx][ny] == 0){
                ++ans;
            }
            visited[nx][ny] |= (1 << dIdx);
            nx += dirs[dIdx];
            ny += dirs[dIdx + 1];
        }
        nx -= dirs[dIdx];
        ny -= dirs[dIdx + 1];
        sweepFloor(room, nx, ny, (dIdx + 1) % 4);
    }
}
