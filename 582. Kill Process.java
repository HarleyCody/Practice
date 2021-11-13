//Best Solution: DFS
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        if(ppid.get(pid.indexOf(kill)) == 0) {
            // starting process is the root process;
            return pid;
        }
    
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = getParentChildrenMap(pid, ppid);
        
        getAllProcess(map, ans, kill);
        
        return ans;
    }
    
    public Map<Integer, List<Integer>> getParentChildrenMap(List<Integer> pid, List<Integer> ppid) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int index = 0; index < pid.size(); index++) {
            Integer currentProcess = pid.get(index);
            Integer parentProcess = ppid.get(index);
            
            List<Integer> childrenProcessList = map.getOrDefault(parentProcess, new ArrayList<Integer>());
            childrenProcessList.add(currentProcess);
            
            map.put(parentProcess, childrenProcessList);
        }
        return map;
    }
                                                            
    public void getAllProcess(Map<Integer, List<Integer>> map, List<Integer> ans, int processToKill) {
        ans.add(processToKill);
        
        if(!map.containsKey(processToKill)) {
            // check if it has any sub process
            return;
        } else {
            for(int childProcess : map.get(processToKill)) {
                getAllProcess(map, ans, childProcess);
            }
        }
    }
}
//My Solution: BFS to extend and save process into ans
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer>[] graph = new ArrayList[50001];
        for(int i = 0; i < pid.size(); ++i){
            int prev = ppid.get(i);
            int cur = pid.get(i);
            if(graph[prev] == null){
                graph[prev] = new ArrayList<Integer>();
            }
            graph[prev].add(cur);
        }
        
        Queue<Integer> q = new LinkedList();
        q.offer(kill);
        
        List<Integer> ans = new ArrayList();
        while(!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);
            if(graph[cur] == null) continue;
            for(int next : graph[cur]){
                q.offer(next);
            }
        }
        
        return ans;
    }
}
