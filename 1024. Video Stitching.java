________________________________________________________My Solution(Greedy)________________________________________________________________
class Solution {
// choose clips with valid start and max end;
// valid start: cur start < last end, so they can connect together
// if maxend <= curstart, cannot extend any more return -1 else do the steps above until start > T;
    public int videoStitching(int[][] clips, int T) {
        int ans = 0, st = 0, end = 0;
        while(st < T){
            for(int i = 0; i < clips.length; ++i){
                if(clips[i][0] > st) continue;
                end = Math.max(end, clips[i][1]);
            }
            if(st >= end) return -1;
            st = end;
            ++ans;
        }
        return ans;
    }
}
________________________________________________________Dp without Sort_______________________________________________________________
class Solution {
    // use difference clips at time i set min for i;
    // update dp[i] with every clips, i is time of termination of clip[j]; so dp[i] = min(dp[i], dp[start] + 1)
    // +1 means use clip which clip[s] <= i && clip[e] >= i;
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;
        for(int i = 1; i <= T; ++i){
            for(int[] clip : clips){
                if(clip[0] <= i && i <= clip[1]){
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == T + 1? -1:dp[T];
    }
}
________________________________________________________dp solution record min at time T________________________________________________________________
class Solution {
// update dp for importing every clips
// sort to set start status
// time that is not covered by previous status is Inf dp[i] = min(inf, dp[start] + 1)
     public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (x, y) -> x[0] - y[0]);
        int[] dp = new int[T + 1];
        Arrays.fill(dp, T + 1);
        dp[0] = 0;
        for(int[] clip : clips){
            for(int i = clip[0] + 1; i <= clip[1] && i <= T; ++i){
                dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
            }
        }
        return dp[T] == T + 1? -1:dp[T];
    }
}
