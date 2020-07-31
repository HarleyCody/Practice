_______________________________________________________________________________Best Solution______________________________________________________________________
//pq O(nlogn)
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int res = 0, i=0, n = stations.length;
        
        int d = startFuel;
        
        while(true)
        {
            while(i < n && d >= stations[i][0])
            {
                pq.offer(stations[i][1]);
                i++;
            }
            
            if(d >= target)
            {
                return res;
            }
            
            if(pq.size() == 0) return -1;
            d+=pq.poll();
            res++;
        }
        
    }
}
________________________________________________________________________________Dp Solution______________________________________________________________________
class Solution {
    //dp[i] farthest distance car can reach with adding i times fuel
    public int minRefuelStops(int target, int startFuel, int[][] s) {
        long[] dp = new long[s.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < s.length; ++i)
            for (int t = i; t >= 0 && dp[t] >= s[i][0]; --t)
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][1]);
        for (int t = 0; t <= s.length; ++t)
            if (dp[t] >= target) return t;
        return -1;
    }
}
