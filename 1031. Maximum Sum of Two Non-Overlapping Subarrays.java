_________________________________________________________Best Solution_________________________________________________________
class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        // sum in index i;
        for(int i = 1;i < A.length;i++){
            A[i] += A[i - 1];
        }
        int ans = A[L + M - 1], lmax = A[L - 1],mmax = A[ M - 1];
        for(int i = L + M; i < A.length; i++){
            // l move right, A[i - M] == head of L, A[i - L - M] == tail of L, head - tail = newSum ofL
            lmax = Math.max(lmax, A[i - M] - A[i - L - M]);
            // r move right. same as L
            mmax = Math.max(mmax, A[i - L] - A[i - L - M]);
            // lmax and mmax are two pointers; l may stop if curL is smaller, here L and M has gap.
            // A[i] - A[i - M] == sumOfM;
            // A[i] - A[i - L] == sumOfL;
            ans = Math.max(ans, Math.max(lmax + A[i] - A[i - M], mmax + A[i] - A[i - L]));
        }
        return ans;
    }
}

____________________________________________________________My Solution________________________________________________________
class Solution {
    // calculate sumOfL and sumOfM
    // try Math.max(sumOfL + sumOfM, ans)
    // try Math.max(sumOfR + sumOfR, ans)
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if(L + M > A.length) return 0;
        //make sure L < M
        if(L > M){
            int tem = L;
            L = M;
            M = tem;
        }
        int[] sumL = new int[A.length], sumM = new int[A.length];
        
        int j = 0, sum = 0;
        while(j < M){
            sum += A[j++];
            if(j == L){
                sumL[L - 1] = sum;
            }
        }
        sumM[M - 1] = sum;
        
        int move = L;
        while(move < A.length){
            sumL[move] = sumL[move - 1] + A[move] - A[move - L];

            if(move > M - 1){
                sumM[move] = sumM[move - 1] + A[move] - A[move - M];
            }
            move++;
        }
        int ans = 0;
        for(int i = L - 1; i < A.length; i++){
            for(j = i + M; j < A.length; j++){
                ans = Math.max((sumL[i] + sumM[j]), ans);
            }
        }
        for(int i = M - 1; i < A.length; i++){
            for(j = i + L; j < A.length; j++){
                ans = Math.max((sumM[i] + sumL[j]), ans);
            }
        }
        return ans;
    } 
}
