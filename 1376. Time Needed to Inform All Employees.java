__________________________________________________________________________________Best Solution__________________________________________________________________________________
class Solution {
    // best solution, traverse every manager to get max, do not need recorder
     public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int res = 0;
        for (int i = 0; i < n; ++i)
            res = Math.max(res, dfs(i, manager, informTime));
        return res;
    }
    public int dfs(int i, int[] manager, int[] informTime) {
        if (manager[i] != -1) {
            informTime[i] += dfs(manager[i], manager, informTime);
            manager[i] = -1;
        }
        return informTime[i];
    }
}
____________________________________________________________________________________My Solution__________________________________________________________________________________
class Solution {
    // recorder[i] record subordiantes of manager i
    // dfs get Max sum path
    List<Integer>[] recorder;
    
    int ans = 0;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if(n == 0){
            return ans;
        }
        
        recorder = new ArrayList[n];
        for(int i = 0; i < n; ++i){
            if(manager[i] == -1){
                continue;
            }
            int man = manager[i];
            if(recorder[man] == null){
                recorder[man] = new ArrayList();
            }
            recorder[man].add(i);
        }
        helper(informTime, headID, 0);
        return ans;
    }
    
    private void helper(int[] inform, int cur, int curTime){
        if(recorder[cur] == null){
            ans = Math.max(curTime, ans);
            return;
        }
        
        int idx = recorder[cur].size();
        for(int i = 0; i < idx; ++i){
            int nextMan = recorder[cur].get(i);
            int nextTime = curTime + inform[cur];
            helper(inform, nextMan, nextTime);
        }
    }
}
