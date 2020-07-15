______________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
    //get height of consecutive 1
    //in collect, treat every 1 as right bottom coner of square
    //detect the max width of h[i], add h[i] to the matrix will increase width number of square
    //so length is the num of square that added by h[i] point
    int R, C;
    public int countSquares(int[][] matrix) {
        R = matrix.length;
        if(R == 0){
            return 0;
        }
        C = matrix[0].length;
        
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(i > 0 && matrix[i][j] == 1){
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        
        int ans = 0;
        for(int[] mat : matrix){
            ans += collect(mat);
        }
        
        return ans;
    }
    
    private int collect(int[] h){
        int ans = 0;
        for(int i = 0; i < C; ++i){
            
            int l = i;
            int n = 0, min = h[l];
            while(l >= 0 && n + 1 <= min){
                ++n;
                --l;
                if(l == -1){
                    break;
                }
                min = Math.min(h[l], min);
            }
            ans += n;
        }
        return ans;
    }
}
