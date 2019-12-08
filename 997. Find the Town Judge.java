class Solution {
// judge will get 1-N points as trust others add point trust by others lose point
    public int findJudge(int N, int[][] trust) {
        int[] trusting = new int[N];
        for(int[] t : trust){
            ++trusting[t[0] - 1];
            --trusting[t[1] - 1];
        }
        // find judge
        for(int i = 0 ; i < N; ++i){
            if(trusting[i] == 1 - N) return i + 1;
        }
        return -1;
    }
}
