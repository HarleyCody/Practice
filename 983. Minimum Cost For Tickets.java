class Solution {
    // dp[i] record minimal costs in day i
    public int mincostTickets(int[] days, int[] costs) {
        // record index of days in the daysArray;
        int[] idxOfDays = new int[366];
        // not appeared days should be -1;
        Arrays.fill(idxOfDays, -1);
        // record idx;
        for(int i = 0; i < days.length; ++i){
            idxOfDays[days[i]]= i;
        }
        // beginning
        idxOfDays[0] = 0;
        // dp[0] is 0. cost nothing.
        int[] dp = new int [days.length + 1];
        dp[0] = 0; 
        // current days == Min(7pass + costs in dp[7 days before], 30 pass + costs in dp[30 days before]), 1pass + cosots in dp[1 day before]
        for(int i = 1; i < dp.length; ++i){
            int pass1 = dp[i-1] + costs[0];
            int start7 = findValidDate(idxOfDays, days[i - 1] - 6);
            int pass7 = dp[start7] + costs[1];
            int start30 = findValidDate(idxOfDays, days[i - 1] - 29);
            int pass30 = dp[start30] + costs[2];
            dp[i] = Math.min(Math.min(pass1, pass7), pass30);
        }
        return dp[dp.length - 1];
    }
    // find idx of days in daysArray, start from i - 6( 7 days before) or i - 29(30 days before); find a valid day with a index > -1;
    private int findValidDate(int[]idxOfDays, int start){
        while(start < 0){
            start++;
        }
        while(idxOfDays[start] == -1){
            ++start;
        }
        return idxOfDays[start];
    }
}
