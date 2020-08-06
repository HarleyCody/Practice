__________________________________________________________________________________________My DP Solution_________________________________________________________________________
class Solution {
    public int minDifficulty(int[] jobs, int days) {
        int tltJob = jobs.length;
        int tltDay = days;
        
        if(tltJob < tltDay){
            return -1;
        }
        
        int[][] dp = new int[tltDay][tltJob];
        dp[0][0] = jobs[0];
        for(int j = 1; j < tltJob; ++j){
            dp[0][j] = Math.max(jobs[j], dp[0][j - 1]);
        }
        
        for(int d = 1; d < tltDay; ++d){
            for(int j = d; j < tltJob; ++j){
                int curMax = 0;
                dp[d][j] = Integer.MAX_VALUE;
                for(int i = j; d <= i; --i){
                    curMax = Math.max(curMax, jobs[i]);
                    dp[d][j] = Math.min(curMax + dp[d - 1][i - 1], dp[d][j]);
                }
            }
        }
        
        return dp[tltDay - 1][tltJob - 1];
    }
}
__________________________________________________________________________________________My Solution_________________________________________________________________________
//DFS + Mem
class Solution {
    int[][] mem;
    int tltDay, tltJob;
    public int minDifficulty(int[] jobs, int d) {
        tltJob = jobs.length;
        tltDay = d;
        if(tltJob < tltDay){
            return -1;
        }
        mem = new int[tltDay][tltJob];
        mem[tltDay - 1][tltJob - 1] = jobs[tltJob - 1];
        for(int i = tltJob - 2; tltDay - 1 <= i; --i){
            mem[tltDay - 1][i] = Math.max(jobs[i], mem[tltDay - 1][i + 1]);
        }
        return doJobs(jobs, 0, 0);
    }
    
    private int doJobs(int[] jobs, int day, int job){
        if(mem[day][job] != 0){
            return mem[day][job];
        }
        
        int curDif = 0, nextDif = 0, most = tltJob - tltDay + day + 1;
        
        int minDif = Integer.MAX_VALUE;
        for(int i = job; i < most; ++i){
            curDif = Math.max(curDif, jobs[i]);
            nextDif = doJobs(jobs, day + 1, i + 1);
            minDif = Math.min(minDif, curDif + nextDif);
        }
        mem[day][job] = minDif;
        return minDif;
    }
}
