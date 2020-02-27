class Solution {
    
    // travel from left to right, calculate length of two creteria;
    // if cur is C1 set start of C2 to next;
    // if cur is C2 set start of C1 to next;
    // length = i + 1 - start + 1 ie i + 2 - start.
    // i + 1 is tail of turbulence, - start is length of turbulance excluding start, + 1 to include start;  
    public int maxTurbulenceSize(int[] A) {
        int len = A.length;
        if(len < 2) return len;
        
        int startRule1 = 0, startRule2 = 0;
        int ans = 0;
        for(int i = 0 ; i < len; ++i){
            // odd
            int curLen = 0;
            if(i + 1 == len){
                    curLen = Math.max(len - startRule1, len - startRule2);
            }else if(i % 2 == 1){
                //odd
                if(A[i] > A[i + 1]){
                    curLen = i + 2 - startRule1;
                    startRule2 = i + 1;
                }else if(A[i] < A[i + 1]){
                    curLen = i + 2 - startRule2;
                    startRule1 = i + 1;
                }else{
                    startRule1 = i + 1;
                    startRule2 = i + 1;
                }
            }else{
                //even
                if(A[i] < A[i + 1]){
                    curLen = i + 2 - startRule1;
                    startRule2 = i + 1;
                }else if(A[i] > A[i + 1]){
                    curLen = i + 2 - startRule2;
                    startRule1 = i + 1;
                }else{
                    startRule1 = i + 1;
                    startRule2 = i + 1;
                }
            }
            ans = Math.max(curLen, ans);
        }
        return ans;
    }
}
