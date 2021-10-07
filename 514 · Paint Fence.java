//Best Solution: More concise
public class Solution {
    public int numWays(int n, int k) {
        // write your code here
        
        int same = 0, diff = k;
        for (int i = 1; i < n; i++) {
            int tmp = diff;
            diff = (diff + same) * (k - 1);
            same = tmp;
        }
        return same + diff;
        
    }
}
//My Solution: Dp, current same = (prevDiff + prevSame) * (k - 1) current diff = prevSame;
public class Solution {
    public int numWays(int n, int k) {
        if(n == 0 || k == 0) return 0;
        if(n == 1) return k;
        int prevSame = k;
        int prevDiff = k * (k - 1);
        int same = k;
        int diff = k * (k - 1);
        for(int i = 2; i < n; ++i){
            same = prevDiff;
            diff = (prevSame + prevDiff) * (k - 1);
            prevSame = same;
            prevDiff = diff;
        }
        return same + diff;
    }
}
