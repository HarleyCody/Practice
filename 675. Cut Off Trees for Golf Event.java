______________________________________________________Best Solution(pq + bfs)__________________________________________________
//use dq to bfs, dq is inserted by detour distance
//min dis = manhatan dis + 2 detour dis. as detour will go out and get back.
class Solution {
    int N;
    int M;
    List<List<Integer>> forest;
    public int cutOffTree(List<List<Integer>> forest) {
        this.forest=forest;
        N = forest.size();
        if(N==0) return 0;
        M = forest.get(0).size();
        if(M==0) return 0;
        PriorityQueue<int[]> pq = getForestByHeight();
        int cost=0;
        int[] prevTree = new int[]{0,0,0};
        while(!pq.isEmpty()){
            int[] nextTree = pq.poll();
            int nextCost=getCost(prevTree,nextTree);
            if(nextCost==-1) return -1;
            prevTree=nextTree;
            cost+=nextCost;
        }
        
        return cost;
    }
    
    private int getCost(int[] prevTree,int[] nextTree){
        boolean[] visited = new boolean[M*N];
        int tR=nextTree[1];
        int tC=nextTree[2];
        ArrayDeque<int[]> dq = new ArrayDeque();
        dq.addFirst(new int[]{0,prevTree[1],prevTree[2]});
        while(!dq.isEmpty()){
            int[] curr = dq.pollFirst();
            int extra = curr[0];
            int sR = curr[1];
            int sC = curr[2];
            if(sR<0 || sC<0 || sR==N || sC==M || visited[sR*M+sC] || forest.get(sR).get(sC)==0 ) continue;
            visited[sR*M+sC] =true;
            // distance = Mathattan dis + detour distance 
            if(sR==tR && sC==tC) return Math.abs(tR-prevTree[1])+Math.abs(tC-prevTree[2])+2*curr[0];
            
            if(sR>tR) dq.addFirst(new int[]{extra,sR-1,sC});
            else dq.addLast(new int[]{extra+1,sR-1,sC});
          
            if(sR<tR) dq.addFirst(new int[]{extra,sR+1,sC});
            else dq.addLast(new int[]{extra+1,sR+1,sC});
           
            if(sC>tC) dq.addFirst(new int[]{extra,sR,sC-1});
            else dq.addLast(new int[]{extra+1,sR,sC-1});
           
            if(sC<tC) dq.addFirst(new int[]{extra,sR,sC+1});
            else dq.addLast(new int[]{extra+1,sR,sC+1});
             
        }
        return -1;
    }
    
    private PriorityQueue<int[]> getForestByHeight(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        int i=0;
        for(List<Integer> row : forest){
            int j=0;
            for(int height:row){
                if(height>1) pq.add(new int[]{height,i,j});
                j++;
            }
            i++;
        }
        return pq;
    }
}
______________________________________________________Best Solution(pq + bfs)__________________________________________________
class Solution {
// mainly different is use Deque to bfs
// calculate abs distance between cur to tar, if 0 return dis otherwise add adjs
// if abs between adj to tar is less than cur to tar, put to head(deque push) otherwise if it is valid put to tail
class Solution {
    int[] dirs = new int[]{0, 1, 0, -1, 0};
    int R, C;
    public int cutOffTree(List<List<Integer>> forest) {
        R = forest.size();
        C = forest.get(0).size();
        
        int[][] rcValue = new int[R * C][];
        int idx = 0;
        for(int i = 0; i < R; ++i){
            List<Integer> f = forest.get(i);
            for(int j = 0; j < C; ++j){
                idx = i * C + j;
                rcValue[idx] = new int[]{i, j, f.get(j)};
            }
        }
        Arrays.sort(rcValue, (x, y) -> x[2] - y[2]);
        int ans = 0;
        int[] cur = new int[]{0, 0};
        for(int i = 0; i < rcValue.length; ++i){
            if(rcValue[i][2] <= 1) continue;
            int[] next = rcValue[i];
            int minSteps = bfs(cur, next, rcValue, forest);
            if(minSteps == -1) return -1;
            ans += minSteps;
            cur = next;
        }
        return ans;
    }
    private int bfs(int[] start, int[] end, int[][] maps, List<List<Integer>> forest){
        boolean[][] visited = new boolean[R][C];
        Deque<int[]> dq = new LinkedList();
        dq.offer(new int[]{start[0], start[1], 0});
        
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            
            if(!visited[cur[0]][cur[1]]){
                visited[cur[0]][cur[1]] = true;
                
                int curToTarget = Math.abs(cur[0] - end[0]) + Math.abs(cur[1] - end[1]);
                if(curToTarget == 0) return cur[2];
                
                for(int j = 0; j < 4; ++j){
                    int nx = cur[0] + dirs[j];
                    int ny = cur[1] + dirs[j + 1];
                    
                    int adjToTarget = Math.abs(nx - end[0]) + Math.abs(ny - end[1]);
                    if(nx < 0 || ny < 0 || nx == R || ny == C || visited[nx][ny] || forest.get(nx).get(ny) == 0) continue;
                // adjToTarget < curToTarget, it must be in map do not need to check overflow. so 56 can swap with line 54
                    if(adjToTarget < curToTarget){
                        dq.push(new int[]{nx, ny, cur[2] + 1});
                    }else{
                        dq.offer(new int[]{nx, ny, cur[2] + 1});
                    }
                }
            }
        }
        return -1;
    }
}
______________________________________________________My Solution(pq + bfs)____________________________________________________
// pq record pos X and Y sorted by tree height in ascend order;
// pq poll is next point need to arrive.
// bfs search for min steps from cur to next
// return sum of every min(cur, next) as total steps
class Solution {
    PriorityQueue<int[]> pq;
    int ans = 0, R = 0, C = 0;
    List<List<Integer>> f;
    int[] dirs = new int[] {0, 1, 0, -1, 0};
    public int cutOffTree(List<List<Integer>> forest) {
        f = forest;
        R = f.size();
        C = f.get(0).size();
        
        pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(forest.get(i).get(j) < 2) continue;
                pq.offer(new int[]{i, j, forest.get(i).get(j)});
            }
        }
        
        int[] cur = new int[]{0, 0};
        while(!pq.isEmpty()){
            int[] next = pq.poll();
            int minSteps = bfs(cur, next, new boolean[R][C]);
            if(minSteps == -1) return -1;
            ans += minSteps;
            cur = next;
        }
        return ans;
    }
    
    private int bfs(int[] current, int[] n, boolean[][] visited){
        int ans = 0;
        LinkedList<int[]> cur = new LinkedList();
        cur.add(current);
        while(!cur.isEmpty()){
            int size = cur.size();
            for(int j = 0; j < size; ++j){
                int[] c = cur.poll();
                if(c[0] == n[0] && c[1] == n[1]) return ans;
                for(int i = 0; i < 4; ++i){
                    int nx = c[0] + dirs[i];
                    int ny = c[1] + dirs[i + 1];
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C ||f.get(nx).get(ny) == 0 || visited[nx][ny])continue;
                    visited[nx][ny] = true;
                    cur.add(new int[]{nx, ny});
                } 
            }
            ++ans;
        }
        return -1;
    }
}
