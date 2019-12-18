_________________________________________________________Best Solution_________________________________________________________
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length ;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0;i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = dfs(matrix, dp, i, j);
            }
        }
       return dp;     
    }
    public static int dfs(int[][] matrix,int[][] dp, int i, int j){
        int m = matrix.length ;
        int n = matrix[0].length;
        // Base Case cur is 0 return 0, any one of top left right bot is 0 return 1
        if(i < 0 || i > m-1 || j < 0 || j > n-1) return 9999;
        
        if(matrix[i][j] == 0) return 0;
        if(i > 0 && matrix[i-1][j] == 0) return 1;
        if(j < n-1 && matrix[i][j+1] == 0) return 1;
        if(i < m-1 && matrix[i+1][j] == 0) return 1;
        if(j>0 && matrix[i][j-1] == 0) return 1;
        
        // Case when no zero around ..do DFS
        int left,bottom,right,top;
        left=top=9999;
        // if recursive call
        if(i > 0 && dp[i-1][j] != 0){
            top = dp[i-1][j];
        }
        if(j> 0 && dp[i][j-1] != 0){
            left = dp[i][j-1];
        }
        bottom = dfs(matrix, dp,i+1, j);
        right = dfs(matrix, dp,i,j+1);
        return Math.min(Math.min(left, right), Math.min(top,bottom))+1; 
    }
}
_________________________________________________________My Solution(bfs when 0)_______________________________________________
class Solution {
// bfs when 0, set 1 to MAX_VALUE
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    public int[][] updateMatrix(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Queue<int[]> recorder = new LinkedList();
        for(int i = 0; i < matrix.length; ++i){
            for(int j = 0; j < matrix[0].length; ++j){
                if(matrix[i][j] == 0){
                    recorder.add(new int[]{i, j});
                }else{
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        // only when nx ny > curX curY + 1 bfs, otherwise(nx, ny is smaller) continue sky nx ny
        while(!recorder.isEmpty()){
            int[] cur = recorder.poll();
            for(int i = 0; i < 4; ++i){
                int nx = cur[0] + dirs[i];
                int ny = cur[1] + dirs[i + 1];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C || matrix[cur[0]][cur[1]] + 1 >= matrix[nx][ny]) continue;
                matrix[nx][ny] = matrix[cur[0]][cur[1]] + 1;
                recorder.add(new int[]{nx, ny});
            }
        }
        return matrix;
    }
}
