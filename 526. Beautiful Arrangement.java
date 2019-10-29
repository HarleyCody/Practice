_________________________________________________________Best Solution________________________________________________________________
class Solution {
    public int countArrangement(int N) {
        int[] pos = new int[N];
        for (int i = 0; i < N; i++) {
            int idx = i + 1, v = 0;
            for (int d = 1, m = 1; d <= N; d++, m <<= 1) {
                if (idx % d == 0 || d % idx == 0) {
                    v += m;
                }                
            }
            // potential allocated postion for i; eg 2 : v = 3(bit 11 means 2 can be allocated at 1 2 th.
            pos[i] = v;
        }
        int[][] dp = new int[N][1 << N];        
        return helper(N, pos, 0, 0, dp);
    }
    
    private int helper(int N, int[] pos, int idx, int mask, int[][] dp) {
        // elements can be allocated position
        int p = pos[idx] ^ (pos[idx] & mask);
        if (p == 0) {
            return 0;
        }
        // find a solution
        if (idx == pos.length - 1) {
            return 1;
        }
        if (dp[idx][mask] > 0) {
            return dp[idx][mask] - 1;
        }
        int ret = 0;
        // m<<= 1 means 
        for (int i = 1, m = 1; i <= N; i++, m <<= 1) {
            // element can be put at i;
            if ((p & m) > 0) {
                ret += helper(N, pos, idx + 1, mask + m, dp);
            }
        }
        // when ret == 0, means cannot find position, so lable it when 1 to avoid caculate it again when dp[idx][mask] > 0, next time return 0 (as 1 - 1) directly,
        dp[idx][mask] = ret + 1;
        return ret;
    }
}
__________________________________________________________My Solution________________________________________________________________
class Solution {
    boolean[] used;
    int ans = 0;
    public int countArrangement(int N) {
        if(N == 1) return 1;
        used = new boolean[N + 1];
        for(int i = 1; i <= N; i++){
            used[i] = true; 
            put(2, N);
            used[i] = false;
        }
        return ans;
    }
    private void put(int idx, int tar){
        for(int i = 1; i <= tar; i++){
            if(!used[i] && (i % idx == 0 || idx % i == 0)){
                if(idx == tar){
                    ans++;
                }else{
                    used[i] = true;
                    put(idx + 1, tar);
                    used[i] = false;
                }
            }
        }
    }
}
