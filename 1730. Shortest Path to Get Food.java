//My Solution: BFS with steps
class Solution {
    public int getFood(char[][] grid) {
        int[] dirs = {0, 1, 0, -1, 0};
        
        int row = grid.length;
        int col = grid[0].length;
        
        int sx = -1, sy = -1;
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(grid[r][c] == '*'){
                    sx = r;
                    sy = c;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList();
        boolean[][] visited = new boolean[row][col];
        
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                int[] curPos = q.poll();
                for(int i = 0; i < 4; ++i){
                    int nx = curPos[0] + dirs[i];
                    int ny = curPos[1] + dirs[i + 1];
                    
                    if(nx < 0 || nx == row || ny < 0 || ny == col || visited[nx][ny] || grid[nx][ny] == 'X') continue;
                    
                    if(grid[nx][ny] == '#'){
                        return steps + 1;
                    }
                    
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            ++steps;
        }
        
        return -1;
    }
}
