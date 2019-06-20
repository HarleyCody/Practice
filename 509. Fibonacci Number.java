class Solution {
    
    public int fib(int N) {
        if(N < 2) return N;
        // record two pre iteratively till count to N
        int pre1 = 1, pre2 = 0, ans = 1;
        for(int i = 2; i <= N; ++i){
            ans = pre1 + pre2;
            pre2 = pre1;
            pre1 = ans;
        }
        return ans;
    }
}
