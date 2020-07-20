______________________________________________________________________Best Solution______________________________________________________________________________
class Solution {
    //dfs find one land
    //bfs build bridge with steps labeled.
    //start from layer2, only expand layer if they are in current layer
    int min = Integer.MIN_VALUE;
    
    public int shortestBridge(int[][] A) {
                
        int result = 0;
        for (int r=0; r<A.length; r++) {
            for (int c=0; c<A.length; c++) {
                if (A[r][c] == 1) {
                    dfs(A, r, c);
                    return bfs(A);
                }
            }
        }
    
        return -1;
    }
    
    public void dfs(int[][] A, int r, int c) {
        if (r < 0 || r >= A.length || c < 0 || c >= A[0].length || A[r][c] != 1) {
            return;
        }
        A[r][c] = 2;
        dfs(A, r+1, c); 
        dfs(A, r-1, c);
        dfs(A, r, c+1);
        dfs(A, r, c-1);
    }
    
    private int bfs(int[][] a) {
        int currentLayer = 2;
        while (true) {
            for (int r = 0; r < a.length; r++) {
                for (int c = 0; c < a.length; c++) {
                    //only consider node in current layer
                    if (a[r][c] != currentLayer) {
                        continue;
                    }
                    //find the other land
                    if ((r > 0 && a[r-1][c] == 1) || (r < a.length-1 && a[r+1][c] == 1) ||
                        (c > 0 && a[r][c-1] == 1) || (c < a.length-1 && a[r][c+1] == 1)) 
                        return currentLayer - 2;
                    
                    if (r > 0 && a[r-1][c] == 0) a[r-1][c] = currentLayer+1;
                    if (c > 0 && a[r][c-1] == 0) a[r][c-1] = currentLayer+1;
                    
                    if (r < a.length-1 && a[r+1][c] == 0) a[r+1][c] = currentLayer+1;
                    if (c < a.length-1 && a[r][c+1] == 0) a[r][c+1] = currentLayer+1;
                }
            }
            ++currentLayer;
        }
    }
}
________________________________________________________________________My Solution______________________________________________________________________________
class Solution {
    //split to two land and calculate one by one
    HashSet<Integer> land0 = new HashSet();
    HashSet<Integer> all = new HashSet();
    int R, C;
    int[] dirs = {-1,0 , 1, 0, -1};
    int[][] map;
    public int shortestBridge(int[][] A) {
        R = A.length;
        C = A[0].length;
        map = A;
        int[] start = new int[2];
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(A[i][j] == 1){
                    int hash = i * 101 + j;
                    all.add(hash);
                    start = new int[]{i, j};
                }
            }
        }
        split(start);
        all.removeAll(land0);
        int ans = 200;
        for(int a : land0){
            int ax = a / 101;
            int ay = a % 101;
            for(int b : all){
                int bx = b / 101;
                int by = b % 101;
                
                int dis = Math.abs(ax- bx) + Math.abs(ay - by) - 1;
                ans = Math.min(dis, ans);
            }
        }
        return ans;
    }
    
    private void split(int[] start){
        Queue<int[]> q = new LinkedList();
        q.offer(start);
        land0.add(start[0] * 101 + start[1]);
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i = 0; i < 4; ++i){
                int nx = cur[0] + dirs[i];
                int ny = cur[1] + dirs[i + 1];
                
                int hash = nx * 101 + ny;
                if(nx < 0 || nx == R || ny < 0 || ny == C || map[nx][ny] != 1 || !land0.add(hash)){
                    continue;
                }
                q.offer(new int[]{nx, ny});
                land0.add(hash);
            }
        }
    }
}
