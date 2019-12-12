_______________________________________________________Best Solution(DFS)___________________________________________________________
class Solution {
    // only record turning point by setting -1 in maze;
    // cannot prun the diverges;
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    boolean[][] cannot;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        cannot = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination);
    }
    
    private boolean dfs(int[][] maze, int[] start, int[] dest){
        if(start[0] == dest[0] && start[1] == dest[1]) return true;
        // visited before
        if(maze[start[0]][start[1]] == -1) return false;
        maze[start[0]][start[1]] = -1;
        
        for(int i = 0; i < 4; ++i){
            // next point
            int nx = start[0];
            int ny = start[1];
            // move until step in wall
            while(-1 < nx && -1 < ny && nx < maze.length && ny < maze[0].length &&
                  maze[nx][ny] != 1){
                nx += dirs[i];
                ny += dirs[i + 1];
            }
            // get out of the wall(turning point)
            nx -= dirs[i];
            ny -= dirs[i + 1];
            // not visited before and able to turn 
            if((cannot[nx][ny] || maze[nx][ny] != - 1) && dfs(maze, new int[]{nx, ny}, dest)) return true;
        }
        
        cannot[start[0]][start[1]] = true;
        return false;
    }
}
_________________________________________________________My Solution(BFS)___________________________________________________________
class Solution {
    // only record turning point as ball have to stop at turnning point
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> adjs = new LinkedList();
        adjs.offer(start);
        while(!adjs.isEmpty()){
            int[] cur = adjs.poll();
            maze[cur[0]][cur[1]] = -1;
            if(cur[0] == destination[0] && cur[1] == destination[1]) return true;
            for(int i = 0; i < 4; ++i){
                int[] next = new int[]{cur[0], cur[1]};
                int nx = next[0] + dirs[i];
                int ny = next[1] + dirs[i + 1];
                while(-1 < nx && -1 < ny && nx < maze.length 
                      && ny < maze[0].length && maze[nx][ny] != 1){
                    next[0] = nx;
                    next[1] = ny;
                    nx += dirs[i];
                    ny += dirs[i + 1];
                }
                if(maze[next[0]][next[1]] != -1){
                    adjs.offer(new int[]{next[0], next[1]});
                }
            } 
        }
        return false;
    }
}
