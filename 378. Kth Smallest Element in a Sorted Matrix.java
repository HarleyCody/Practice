____________________________________________________________Best Solution____________________________________________________________________
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        int lo=matrix[0][0], hi=matrix[n-1][n-1];
        // use binay search to find num with kth smallest numbers
        while(lo<=hi){
            int mid=lo+ (hi-lo)/2;
            int count=getLessK(matrix, mid);
            if(count<k){
                lo=mid+1;
            }
            else {
                hi=mid-1;
            }
        }
        return lo;
    }
    private int getLessK(int[][] matrix, int c){
        int i=matrix.length-1;
        int j=0;
        int res=0;
        while(i >= 0 && j < matrix.length){
            if(matrix[i][j] > c){
                i--;
            }
            // cur < tar; ans + whole row to i(i,e i + 1);
            else{
                j++;
                res += i+1;
            }
        }
        return res;
    }
}
_____________________________________________________________My Solution____________________________________________________________________
class Solution {
// 2d to 1d and sort return k - 1;
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        int[] recorder = new int[len * len];
        int i = 0;
        for(int[] row : matrix){
            for(int val : row){
                recorder[i++] = val;
            }
        }
        Arrays.sort(recorder);
        return recorder[k - 1];
    }
}
