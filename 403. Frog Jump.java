__________________________________________________________Best Solution___________________________________________________________________
class Solution {
    //dfs, binary search next() in range of idx ~ n(ie, moving forward)
    //find next point can be jumped to, continue untill no next points avaliable
    int n;
    public boolean canCross(int[] stones) {
        n = stones.length;
        for (int i = 1; i < n; ++i) if (stones[i] - stones[i - 1] > i) return false;
        return dfs(stones, 0, 1);
    }
    
    private boolean dfs(int[] stones, int idx, int jump) {
        if (idx == n - 1) return true;
        if (idx < 0 || idx == n || jump <= 0) return false;
        int cur = stones[idx] + jump;
        return dfs(stones, Arrays.binarySearch(stones, idx, n, cur + 1), jump + 1) ||
            dfs(stones, Arrays.binarySearch(stones, idx, n, cur), jump) ||
            dfs(stones, Arrays.binarySearch(stones, idx, n, cur - 1), jump - 1);
    }
}
____________________________________________________________My Solution___________________________________________________________________
class Solution {
    // HashMap<Key, Value> value records steps that were taken to reach to key
    int[] dirs = {1, 0, -1};
    public boolean canCross(int[] stones) {
        if(stones.length == 0){
            return true;
        }
        int len = stones.length, max = stones[len - 1];
        HashMap<Integer, HashSet<Integer>> steps = new HashMap();
        for(int i = 0; i < len; ++i){
            steps.put(stones[i], new HashSet<Integer>());
        }

        steps.get(0).add(0);
        for(int i = 0; i < len; ++i){
            if(!steps.containsKey(stones[i])){
                continue;
            }
            for(int step : steps.get(stones[i])){
                for(int d = 0; d < 3; ++d){
                    int nextStep = step + dirs[d];
                    int nextStone = stones[i] + nextStep;
                    // move forward
                    if(nextStep <= 0 || nextStone > max){
                        continue;
                    }
                    if(nextStone == max){
                        return true;
                    }
                    if(steps.containsKey(nextStone)){
                        steps.get(nextStone).add(nextStep);
                    }
                }
            }
        }
        return false;
    }
}
