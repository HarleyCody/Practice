___________________________________________________________________________________Best Solution________________________________________________________________________
//idea is same as mine
//O(n^2) but use array not tree map, it will be faster in insert slower in find
//find first legit job to concatenate to get max profit at time curJob.end
class Job implements Comparable<Job>{
    int start;
    int end;
    int profit;
    
    Job(int start, int end, int profit)  {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }
    
    @Override
    public int compareTo(Job o) {
        return this.end - o.end;
    }    
}
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for(int i=0; i<n; i++){
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs);
        
        int[] dp = new int[n];
        dp[0] = jobs[0].profit;
        for(int i = 1; i < n; i++)  {
            dp[i] = Math.max(jobs[i].profit, dp[i-1]);
            for(int j=i-1; j>=0; j--)  {
                if(jobs[j].end <= jobs[i].start)  {
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i].profit);
                    break;
                }
            }
        }
        return dp[n-1];    
    }
}
____________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // dp O(nlogn)
    // current max == max(job profit before start + current Job profit, job profit end before current end);
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = startTime.length;
        TreeMap<Integer, Integer> recorder = new TreeMap();
        
        int[][] jobs = new int[len][3];
        for(int i = 0; i < len; ++i){
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        
        Arrays.sort(jobs, (x, y) -> x[1] == y[1] ? x[0] - y[0] : x[1] - y[1]);
        int ans = 0;
        int pPro, cPro, curPro;
        for(int i = 0; i < len; ++i){
            Integer pKey = recorder.floorKey(jobs[i][0]);
            Integer cKey = recorder.floorKey(jobs[i][1]);

            pPro = pKey == null ? 0 : recorder.get(pKey);
            cPro = cKey == null ? 0 : recorder.get(cKey);
            curPro = Math.max(cPro, pPro + jobs[i][2]);
            recorder.put(jobs[i][1], curPro);
            
            ans = Math.max(curPro, ans);
        }
        return ans;
    }
}
