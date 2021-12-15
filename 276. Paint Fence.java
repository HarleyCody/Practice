//My Solution: DP, currentSame = previous diff;
//                   currentDiff = (previous same + previous diff) * (K - 1); -1 is previous color 
class Solution {
    public int numWays(int n, int k) {
        int prevSame = 0;
        int prevDiff = k;
        int curSame = 0;
        int curDiff = k;
        
        for(int i = 1; i < n; ++i){
            curSame = prevDiff;
            curDiff = (prevSame + prevDiff) * (k - 1);
            prevDiff = curDiff;
            prevSame = curSame;
        }
        
        return curSame + curDiff;
    }
}
