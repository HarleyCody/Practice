class Solution {
    public int maximalSquare(char[][] a) {
        if(a.length == 0) return 0;
        int m = a.length, n = a[0].length, result = 0;
        int[][] b = new int[m+1][n+1];
        for (int i = 1 ; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(a[i-1][j-1] == '1') {
                    // b[i][j] represents largest Square with a[i-1][j-1] as bottom right point of sqaure.
                    // Math.min assures grids in squares all are 1. Can be formed as square
                    // length of square with bottomright a[i-1][j-1] depends on min of top, left and topleft dp[i][j]
                    // plus 1 beacuse it is square, the length could be extend as a[i-1][j-1] is 1
                    b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
                    result = Math.max(b[i][j] , result); // update result
                }
            }
        }
        for(int i = 0; i < b.length; ++i){
            for(int j = 0; j < b[0].length; ++j){
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
        return result*result;
    }
}
