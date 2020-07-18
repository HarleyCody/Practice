______________________________________________________________________My Solution 2________________________________________________________________________________
class StockSpanner {
    // similar to stack but use array
    // store rlt of every next, so jump to the previous peak point directly instead of --d
    // collect rlt from peak point only
    int[] recorder = new int[100001];
    int[] mem = new int[100001];
    int curIdx = 0;
    public StockSpanner() {
        
    }
    
    public int next(int price) {
        if(curIdx == 0 || recorder[curIdx - 1] > price){
            mem[curIdx] = 1;
        }else{
            mem[curIdx] = mem[curIdx - 1] + 1;
        }
        
        int ans = 0, idx = curIdx;
        recorder[curIdx++] = price;
        while(idx >= 0 && recorder[idx] <= price){
            ans += mem[idx];
            idx -= mem[idx];
        }
        
        return ans;
    }
}
______________________________________________________________________My Solution________________________________________________________________________________
class StockSpanner {
    //Store and search the price directly
    int[] recorder = new int[100001];
    int curIdx;
    public StockSpanner() {
        curIdx = 0;
    }
    
    public int next(int price) {
        int idx = curIdx;
        recorder[curIdx++] = price;
        while(idx >= 0 && recorder[idx] <= price){
            --idx;
        }
        
        return curIdx - idx - 1;
    }
}
