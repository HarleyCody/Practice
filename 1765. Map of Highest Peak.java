//Best Solution: BFS by array use array to be queue and two pinters start, end to memic offer and poll
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[] x = new int[n * m];
        int[] y = new int[n * m];
        int start = 0, end = -1;
        int[][] ans = new int[m][n];
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (isWater[i][j] == 1) {
                    end++;
                    ans[i][j] = 0;
                    x[end] = i;
                    y[end] = j;
                } else
                    ans[i][j] = -1;
        while (start <= end) {
            int i = x[start];
            int j = y[start];
            if (i > 0 && ans[i-1][j] == -1) {
                ans[i-1][j] = ans[i][j] + 1;
                end++;
                x[end] = i-1;
                y[end] = j;
            }
            if (i < m-1 && ans[i+1][j] == -1) {
                ans[i+1][j] = ans[i][j] + 1;
                end++;
                x[end] = i+1;
                y[end] = j;
            }
            if (j > 0 && ans[i][j-1] == -1) {
                ans[i][j-1] = ans[i][j] + 1;
                end++;
                x[end] = i;
                y[end] = j-1;
            }
            if (j < n-1 && ans[i][j+1] == -1) {
                ans[i][j+1] = ans[i][j] + 1;
                end++;
                x[end] = i;
                y[end] = j+1;
            }
            start++;
        }
        return ans;
    }
}
//My Solution: BFS flood land to build height
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        Queue<int[]> lower = new LinkedList();
        int[] dirs = new int[]{-1, 0, 1, 0,-1};
        int R = isWater.length;
        int C = isWater[0].length;
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(isWater[r][c] == 1){
                    lower.offer(new int[]{r, c});
                    isWater[r][c] = 0;
                }else{
                    isWater[r][c] = -1;
                }
            }
        }
        int height = 1;
        while(!lower.isEmpty()){
            int size = lower.size();
            while(size-- > 0){
                int[] cur = lower.poll();
        
                for(int i = 0 ; i < 4; ++i){
                    int nx = cur[0] + dirs[i];
                    int ny = cur[1] + dirs[i + 1];
                    if(nx < 0 || nx == R || ny < 0 || ny == C || isWater[nx][ny] != -1){
                        continue;
                    }
                    lower.offer(new int[]{nx, ny});
                    isWater[nx][ny] = height;                    
                }
            }
            ++height;
        }
        return isWater;
    }
}
