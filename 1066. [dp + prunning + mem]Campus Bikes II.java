__________________________________________________Best Solution_______________________________________________________________
class Solution {
    // record minimal distance of workers after i use rest bikes.
    // bitmaps covers all kinds of situation. eg mem[0] will cover w:1 2 3 use b:1 2 3 or b:1 3 2 etc. and store minValue;
    int[] mem;
    public int assignBikes(int[][] workers, int[][] bikes) {
        mem = new int[1 << bikes.length];
        return dfs(workers, 0, bikes, 0);
    }
    private int dfs(int[][] workers, int i, int[][] bikes, int state){
        if(mem[state] > 0) return mem[state];
        if(i == workers.length) return 0;
        int min = Integer.MAX_VALUE;
        for(int j = 0; j < bikes.length; ++j){
            if((state & (1 << j)) > 0) continue;
            // label jth bike
            state |= (1 << j);
            int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
            min = Math.min(min, dfs(workers, i + 1, bikes, state) + dist);
            // unlabled jthbike;
            state &= ~(1 << j);
        }
        return mem[state] = min;
    }
}

___________________________________________________My Solution________________________________________________________________
class Solution {
    // backtrack
    // recorder record the distance from worker to bikes(k:worker, v:bikes)
    HashMap<int[], int[]> recorder = new HashMap();
    // record visited bikes, faster than hashset.
    boolean[] used;
    // med: sum of manhattan distance in different combination.
    int ans = Integer.MAX_VALUE, med = 0;
    public int assignBikes(int[][] workers, int[][] bikes) {
        if(workers.length == 0) return 0;
        used = new boolean[bikes.length];
        for(int[] worker : workers){
            HashMap<Integer, Integer> disMap = new HashMap();
            int[] dis = new int[bikes.length]; 
            for(int i = 0; i < bikes.length; i++){
                dis[i] = Math.abs(worker[0] - bikes[i][0]) + Math.abs(worker[1] - bikes[i][1]);
            }
            recorder.put(worker, dis);
        }
        int[] dis = recorder.get(workers[0]);
        int temp = med;
        for(int i = 0; i < dis.length; i++){
            med += dis[i];
            used[i] = true;
            assignBikes(workers, recorder.size(), 1);
            med = temp;
            used[i] = false;
        }
        return ans;
    }
    private void assignBikes(int[][] workers, int size, int curIdx){
        if(curIdx == size){
            ans = Math.min(ans, med);
            return;
        }
        int[] bikes = recorder.get(workers[curIdx]);
        int temp = med;
        for(int i = 0; i < bikes.length; i++){
            if(used[i]) continue;
            
            med += bikes[i];
            used[i] = true;
            assignBikes(workers, size, curIdx + 1);
            med = temp;
            used[i] = false;
        }
    }
}
