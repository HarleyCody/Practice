class Solution {
    public int findCircleNum(int[][] M) {
        // check every people's friend list
        int times = M.length;
        int[] checked = new int[times];
        int ans = 0;
        for(int i = 0; i < times; ++i){
            if(checked[i] == 0){
                ++ans;
                checkCircle(checked, M, i);
            }
        }
        return ans;
    }
    // eliminate friends and friends' friends and so on.
    // dfs
    private void checkCircle(int[] checked, int[][] M, int idx ){
        if (checked[idx] == 1) return;
        checked[idx] = 1;
        for(int i = 0; i < M.length; ++i){
            if(M[idx][i] == 1)
                checkCircle(checked, M, i);
        }
    }
}
