_______________________________________________________________Best Solution______________________________________________________________
public class Solution {
    // calcualte numofLands and numOfNeighbor(only in right or down ward), 
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }
        return islands * 4 - neighbours * 2;
    }
}
________________________________________________________________My Solution_______________________________________________________________
class Solution {
    // calcualte one by one;
    public int islandPerimeter(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        
        if(R == 0 || C == 0) return 0;
        
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(grid[i][j] == 1){
                    for(int d = 0; d < 4; ++d){
                        int nx = i + dirs[d];
                        int ny = j + dirs[d + 1];
                        if(nx == R || nx == -1 || ny == C || ny == -1 || grid[nx][ny] == 0){
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
