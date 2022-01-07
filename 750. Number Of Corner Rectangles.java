//Best Solution: recorder pair of one of same row, similar as sliding window count the frequence;
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        
        int[][] freqs = new int[col][col];
        int ans = 0;
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                if(grid[r][c] == 0) continue;
                for(int k = c + 1; k < col; ++k){
                    if(grid[r][k] == 0) continue;
                    ans += freqs[c][k];
                    ++freqs[c][k];
                }
            }
        }
        return ans;
    }
}
//My Solution: find row by row and add only when four coner are 1
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        
        int[] sum = new int[col];
        int[] freq;
        int ans = 0;
        for(int r = 0; r < row; ++r){
            for(int d = 1; r + d < row; ++d){
                freq = new int[col + 1];
                for(int c = 0; c < col; ++c){
                    sum[c] = grid[r + d][c] + grid[r][c];
                    if(sum[c] == 2){
                        ans += freq[sum[c]];
                        ++freq[sum[c]];
                    }
                }
            }
        }
        
        return ans;
    }
}
