______________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
    // count and set to get min;
    // break down math.min, so it will be faster
    int ans = 0;
    public int longestLine(int[][] M) {
        int R = M.length;
        if(R == 0){
            return 0;
        }
        int C = M[0].length;
        int[][] dia = new int[R][C];
        int[][] ant = new int[R][C];
        int[][] row = new int[R][C];
        int[][] col = new int[R][C];
        
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(M[i][j] == 0){
                    continue;
                }
                if(0 == i){
                    dia[0][j] = ant[0][j] = col[0][j] = row[0][j] = 1 ;
                    if(0 < j){
                        row[0][j] = row[0][j - 1] + 1;
                    }
                    ans = Math.max(row[0][j], ans);
                }else if(i < R){
                    col[i][j] = col[i - 1][j] + 1;
                    row[i][j] = dia[i][j] = ant[i][j] = 1;
                    if(0 == j && j < C - 1){
                        ant[i][j] = ant[i - 1][j + 1] + 1;
                        ans = Math.max(ant[i][j], ans);
                    }else if(0 < j && j < C){
                        dia[i][j] = dia[i - 1][j - 1] + 1;
                        row[i][j] = row[i][j - 1] + 1;
                        ans = Math.max(Math.max(dia[i][j], row[i][j]), ans);
                        if( j < C - 1){
                            ant[i][j] = ant[i - 1][j + 1] + 1;
                            ans = Math.max(ans, ant[i][j]);
                        }

                    }
                    ans = Math.max(col[i][j], ans);
                }
            }
        }
        return ans;
    }
}
