//My Solution: DP next level derives from upper level, dp[r][c] give its exceed to dp[r + 1][c] and dp[r + 1][c + 1]
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass){
        int row = query_row + 1;
        int col = query_glass + 1;
        double[] vol = new double[col];
        double[] nextVol = new double[col];
        vol[0] = poured;
        for(int r = 1; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(vol[c] <= 1) continue;
                
                double exceed = vol[c] - 1;
                nextVol[c] += exceed / 2;
                
                if(c + 1 == col) continue;
                nextVol[c + 1] = exceed / 2;
            }
            vol = nextVol;
            nextVol = new double[col];
        }
        
        return Math.min(1, vol[col - 1]);
    }
}
