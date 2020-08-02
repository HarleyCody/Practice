_____________________________________________________________________2Sum Solution__________________________________________________________________________________________
class Solution {
    // Scan from left to right, every row changes to presum in that row;
    // treat every row as a 1D sum use two sum way to find how many times in this col find a val = cur - tar, add ans with times;
    // conlsively transfer 2D array to prefix of every row then detect row by row in range(leftColumn, rightColumn) with 2Sum way
    // find val = cur - tar instead of val = tar - cur, as cur is prevsum at that point, it need to find a submatrix to delete.
    public int numSubmatrixSumTarget(int[][] A, int target) {
        int res = 0, m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 1; j < n; j++)
                A[i][j] += A[i][j - 1];
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
                    res += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
______________________________________________________________________My Solution__________________________________________________________________________________________
// Brute Force
class Solution {
    // image it as 1D, area sum = endSum - preSum;
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int R = matrix.length, C = matrix[0].length;
        int[][] sum = new int[R][C];
        
        sum[0][0] = matrix[0][0];
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(r == 0 && c == 0){
                    continue;
                }
                sum[r][c] = matrix[r][c];
                if(0 < r && 0 < c){
                    sum[r][c] += sum[r][c - 1] + sum[r - 1][c] - sum[r - 1][c - 1];
                }else if(c == 0){
                    sum[r][c] += sum[r - 1][c];
                }else{
                    sum[r][c] += sum[r][c - 1];
                }
            }
        }
        int ans = 0;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                for(int x = r; x < R; ++x){
                    for(int y = c; y < C; ++y){
                        int area = 0;
                        int prev = r != 0 && c != 0 ? sum[r - 1][c - 1] : 0;
                        int left = c == 0 ? 0 : sum[x][c - 1];
                        int above = r == 0 ? 0 : sum[r - 1][y];
                        
                        area = sum[x][y] - left - above + prev;
                        
                        if(area == target){
                            ++ans;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
