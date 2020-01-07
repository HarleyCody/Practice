_____________________________________________________________Best Solution(DFS)___________________________________________________________
class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
            }
        }
    }
    
    
    public void dfs(int[][] rooms, int i, int j, int d) {
        if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < d) return;
        rooms[i][j] = d;
        dfs(rooms, i - 1, j, d + 1);
        dfs(rooms, i, j - 1, d + 1);
        dfs(rooms, i + 1, j, d + 1);
        dfs(rooms, i, j + 1, d + 1);
    }
}
________________________________________________________________My Solution_______________________________________________________________
class Solution {
// bfs
    int[] dirs = {0, 1, 0, -1, 0};
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> q = new LinkedList();
        int r = rooms.length;
        int c = rooms[0].length;
        for(int i = 0 ; i < r; ++i){
            for(int j = 0; j < c; ++j){
                if(rooms[i][j] == 0){
                    q.add(new int[]{i, j});
                }
            }
        }
        int steps = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; ++i){
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                for(int d = 0; d < 4; ++d){
                    int nx = x + dirs[d];
                    int ny = y + dirs[d + 1];
                    if(nx < 0 || ny < 0 || nx == r || ny == c || rooms[nx][ny] != 2147483647 || rooms[nx][ny] == -1)continue;
                    rooms[nx][ny] = steps;
                    q.add(new int[]{nx, ny});
                }
            }
            ++steps;
        }
    }
}
