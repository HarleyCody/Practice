____________________________________________________________________________________My Solution___________________________________________________________________________
// Backtrack compose int digit by digit
// Duplicate with K == 0
// Add 0 when N == 1;
class Solution {
    List<Integer> ansList = new ArrayList();
    int N, K;
    public int[] numsSameConsecDiff(int N, int K) {
        this.N = N;
        this.K = K;
        for(int d = 1; d <= 9; ++d){
            expand(d, 1, d);
        }
        
        if(N == 1){
            ansList.add(0);
        }
        int size = ansList.size();
        int[] ans = new int[size];
        
        for(int i = 0; i < size; ++i){
            ans[i] = ansList.get(i);
        }
        
        return ans;
    }
    
    private void expand(int pDigit, int len, int cur){
        if(len == N){
            ansList.add(cur);
            return;
        }
        
        int cDigit = pDigit + K;
        if(cDigit < 10){
            cur = cur * 10 + cDigit;
            expand(cDigit, len + 1, cur);
            cur /= 10;
        }
        cDigit = pDigit - K;
        if(cDigit > -1 && K != 0){
            cur = cur * 10 + cDigit;
            expand(cDigit, len + 1, cur);
            cur /= 10;
        }
    }
}
