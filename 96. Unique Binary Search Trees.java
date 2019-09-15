class Solution {
    // record possbility in tree with length; recorder[length] = possibility
    // reduce computing
    int[] recorder;
    public int numTrees(int n) {
        if(n < 3) return n;
        
        recorder = new int[n + 1];
        recorder[1] = 1;
        recorder[2] = 2;
        recorder[3] = 5;
        
        int ans = 0;
        int pos = 0;
        // answer is symmetric with difference head, 
        /*eg, n = 5; head 1: pos 14 == head 5: pos 14
                     head 2: pos 5 == head 4: pos 5; head 3 : pos 4 
                     total pos :42*/
        // so only need to double n / 2 head
        int end = n / 2;
        
        // as head prior to mid(n + 1) / 2 will be doulbled, so only need to acculmulate to mid.
        for(int i = 1; i <= (n + 1) / 2; i++){
            pos = 0;
            pos += possibility(1, i);
            if(pos == 0 || possibility(i + 1, n + 1) == 0){
                pos += possibility(i + 1, n + 1);
            }else{
                pos *= possibility(i + 1, n + 1);
            }
            if(i <= end){
                pos *= 2;
            }
            ans += pos;
        }
        return ans;
    }
    // recursively calcualte possibility
    /* pos(head) = pos(left) * pos(right);
        range is need as it may be in the middle as range (2, 5) 
    */
    private int possibility(int start, int end){
        int length = end - start;
        if(length == 0) return 0;
        // possibility for length has been calculated, return record directly.
        if(recorder[length] != 0)  return recorder[length];
        // calculate possibility
        int ans = 0;
        int pos = 0;
        for(int i = start; i < end; i++){
            pos = 0;
            pos += possibility(start, i);
            if(pos == 0 || possibility(i + 1, end) == 0){
                pos += possibility(i + 1, end);
            }else{
                pos *= possibility(i + 1, end);
            }
            ans += pos;
        }
        // store calculated possibility to length;
        recorder[length] = ans;
        return ans;
    }
}
