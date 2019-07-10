_________________________________________________________Best Solution(DFS____________________________________________________
class Solution {
    // dfs start from boarder of each ocean, try to expand area of oceans;
    // if both ocean contains same area, the same are is answers, add them to ansList
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList();
        if(matrix.length == 0) return ans;
        int rlen = matrix.length;
        int clen = matrix[0].length;
        
        // record are of oceans
        boolean[][] pacific = new boolean[rlen][clen];
        boolean[][] atlantic = new boolean[rlen][clen];
        
        // start from boarder to expand ocean;
        for(int i = 0; i < rlen; ++i){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, clen - 1);
        }
        // start from boarder to expand ocean;
        for(int i = 0; i < clen; ++i){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, rlen - 1, i);
        }
        for(int i = 0; i < rlen; ++i){
            for(int j = 0; j < clen; ++j){
                if(pacific[i][j] && atlantic[i][j]){
                    ans.add(Arrays.asList(i,j));
                }
            }
        }
        return ans;
    }
    
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y){
        int rlen = matrix.length, clen = matrix[0].length;
        // out of array or current height is smaller than previous height, return null;
        if(x < 0 || x >= rlen || y < 0 || y >= clen || visited[x][y] || matrix[x][y] < height){
            return;
        }
        // valid area(reachable)
        visited[x][y] = true;
        // next area 
        for(int[] d : dirs){
            dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
        }
    }
}
_________________________________________________________BFS Solution_________________________________________________________
class Solution {
    // bfs, start from boarder of each one, tries to expand, and record expanded area.
    // if an area is recorded by expanding pacific and atlantic, then, it is one of answer
    // bfs use queue to store every nodes. 
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList();
        if(matrix.length == 0) return ans;
        int m = matrix.length;
        int n = matrix[0].length;
        
        // record area of pacific
        Queue<int[]> pacific = new LinkedList();
        // record area of atlantic
        Queue<int[]> atlantic = new LinkedList();
        // areas of pacific and atlantic
        boolean[][] atPacific = new boolean[m][n];
        boolean[][] atAtlantic = new boolean[m][n];
        
        for(int i = 0; i < m; ++i){
            pacific.offer(new int[]{i, 0});
            atlantic.offer(new int[]{i, n - 1});
            atPacific[i][0] = true;
            atAtlantic[i][n - 1] = true;
        }
        
        for(int i = 0; i < n; ++i){
            pacific.offer(new int[]{0, i});
            atlantic.offer(new int[]{m - 1, i});
            atPacific[0][i] = true;
            atAtlantic[m - 1][i] = true;
        }
        bfs(matrix, pacific, atPacific);
        bfs(matrix, atlantic, atAtlantic);
        for(int i = 0; i < m; ++i){
            for(int j = 0; j < n; ++j){
                if(atPacific[i][j] && atAtlantic[i][j]){
                    ans.add(Arrays.asList(i,j));
                }
            }
        }
        return ans;
    }
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0},{-1, 0}};
    private void bfs(int[][] matrix, Queue<int[]> ocean, boolean[][] visited){
        int m = matrix.length;
        int n = matrix[0].length;
        while(!ocean.isEmpty()){
            int[] cur = ocean.poll(); 
            for(int[] d : dirs){
                int nextX = cur[0] + d[0];
                int nextY = cur[1] + d[1];
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY] || matrix[nextX][nextY] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[nextX][nextY] = true;
                ocean.offer(new int[]{nextX, nextY});
            }
        }
    }
}
