________________________________________________________Best Solution__________________________________________________________
class Solution {
// reocrd dirctions change of path of island
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    int[][] map;
    HashSet<String> recorder = new HashSet();
    public int numDistinctIslands(int[][] grid) {
        map = grid;
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j < grid[0].length; ++j){
                if(map[i][j] == 1){
                    StringBuilder path = sinkIsland(i, j, new StringBuilder(), 's');
                    if(!recorder.add(path.toString()))continue;
                }
            }
        }
        return recorder.size();
    }
    private StringBuilder sinkIsland(int x, int y, StringBuilder path, char dir){
        path.append(dir);
        map[x][y] = 0;
        for(int i = 0; i < 4; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            if(nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length || map[nx][ny] == 0) continue;
            sinkIsland(nx, ny, path, (char)i);
        }
        // boarder of island;
        return path.append('b');
    }
}
