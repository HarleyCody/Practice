______________________________________________________________________DP Solution________________________________________________________________________
class Solution {
    // 2d max sum to 1d max sum
    // dp record every row sum from col l to r;
    // traverse dp to get max sum;
    // find subarray in dp that sum <= k
    // detect MaxSum of dp; if rlt <= k then that is the rlt in that dp
    // if rlt > k need to find max subarray by O(n^2)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix==null||matrix.length==0||matrix[0].length==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        
        for (int l = 0; l < n; ++l) {
            int[] dp = new int[m];
            
            for (int r = l ; r < n ; ++r) {
                for (int i = 0 ; i < m ; ++i) {
                    dp[i] += matrix[i][r];
                }
                max = Math.max(sum(dp, k), max);
            }
        }
        
        return max;
    }
    
    public int sum(int[] dp, int k) {
        int max = Integer.MIN_VALUE;
        int sum = dp[0], summax = sum;
        
        for (int i = 1; i < dp.length; ++i) {
            if (sum > 0) sum += dp[i];
            else sum = dp[i];
            if (sum > summax) summax = sum;
        }
        if (summax <= k) return summax;
        
        for (int i = 0; i < dp.length; ++i) {
            int subsum = 0;
            for  (int j = i; j < dp.length; ++j) {
                subsum += dp[j];
                if (subsum <= k && max < subsum) max = subsum;
            }
        }
        
        return max;
    }
}
______________________________________________________________________Binary Seach Solution________________________________________________________________________
m^2*n*log(m * n)
class Solution {
    // use min length as row max length as col
    // scan line by line record the sum in matrix[i][j], sum is rectange from 0,0 to i, j
    // use treeset to record max valid rest value,(two sum), scan a sum store it;
    public int maxSumSubmatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0)return 0;
        int col = matrix[0].length;
        int m = Math.min(row,col);
        int n = Math.max(row,col);
        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < m; ++i){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i; j >= 0; --j){
                int val = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                //traverse every column/row and sum up
                for(int k = 0; k < n; ++k){
                    array[k] = array[k] + (colIsBig ? matrix[j][k] : matrix[k][j]);
                    val += array[k];
                    //use  TreeMap to binary search previous sum to get possible result 
                    Integer subres = set.ceiling(val - target);
                    if(null != subres){
                        res = Math.max(res, val - subres);
                    }
                    set.add(val);
                }
            }
        }
        return res;
    }
}
________________________________________________________________________My Solution________________________________________________________________________
//Prefix sum
//Attention: matrix[r][c] to matrix[i][c] include r c, so it will delete from r - 1 above and c - 1 left then add overlap area
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int R = matrix.length, C = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(r == 0 && c == 0){
                    continue;
                }
                if(r == 0){
                    matrix[0][c] += matrix[0][c - 1];
                }else if(c == 0){
                    matrix[r][0] += matrix[r - 1][0];
                }else{
                    matrix[r][c] += matrix[r - 1][c] + matrix[r][c - 1] - matrix[r - 1][c - 1];
                }
            }
        }
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                for(int i = r; i < R; ++i){
                    for(int j = c; j < C; ++j){

                        int curSum = 0;
                        if(r == 0 && c == 0){
                            curSum = matrix[i][j];
                        }else if(r == 0){
                            curSum = matrix[i][j] - matrix[i][c - 1];
                        }else if(c == 0){
                            curSum = matrix[i][j] - matrix[r - 1][j];
                        }else{
                            curSum = matrix[i][j] + matrix[r - 1][c - 1] - matrix[r - 1][j] - matrix[i][c - 1];
                        }
                        if(ans < curSum && curSum <= k){
                            ans = curSum;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
