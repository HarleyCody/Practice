//Best Solution: Make people around available pos around the box directly, do not count the walk distance.
//Detect reachable by bfs and find status by bfs as well. bfs is nested in bfs
//Record the position with direction by 2D array
class Solution {
    int xv[] = {-1,0,1,0};
    int yv[] = {0,1,0,-1};
    public int minPushBox(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int pos[] = new int[2];
        int target = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                char c = grid[i][j];
                if(c=='B'){
                    pos[0] = i*m+j;
                }
                if(c=='S'){
                    pos[1] = i*m+j;
                }
                if(c=='T'){
                    target = i*m+j;
                }
            }
        }
        return bfs(pos,grid,target,n,m);
    }
    public int bfs(int[] pos, char[][] grid,int target, int n, int m){
        int v[][] = new int[n*m][4];
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(pos);
        int ans = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            while(qSize-- > 0){
                int[] par = q.remove();
                if(par[0]==target) return ans;
                for(int k=0;k<4;k++){
                    int bx = xv[k] + par[0]/m;
                    int by = yv[k] + par[0]%m;
                    int px = -xv[k] + par[0]/m;
                    int py = -yv[k] + par[0]%m;
                    if(isValidPos(bx,by,n,m) && isValidPos(px,py,n,m) &&
                       grid[bx][by]!='#' && v[bx*m+by][k]==0 &&
                       isPersonReachable(par,new int[]{px,py},n,m,grid)) {
                        int[] child = new int[]{bx*m+by,par[0]};
                        q.add(child);
                        v[bx*m+by][k] = 1;
                    }
                }
            }
            ++ans;
            
        }
        return -1;
    }
    public boolean isPersonReachable(int par[], int b[], int n, int m, char[][] grid){
        if(grid[b[0]][b[1]]=='#') return false;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(par[1]);
        int v[] = new int[n*m];
        while(!q.isEmpty()){
            int cur = q.remove();
            int i = cur/m;
            int j = cur%m;
            if(i==b[0] && j==b[1]) return true;
            for(int k=0;k<4;k++){
                int x = i + xv[k];
                int y = j + yv[k];
                if(isValidPos(x,y,n,m) && v[x*m+y]==0 &&
                  grid[x][y]!='#' && x*m+y!=par[0]){
                    v[x*m+y] = 1;
                    q.add(x*m+y);
                }
            }
        }
        return false;
    }
    
    public boolean isValidPos(int px, int py, int n, int m){
        if(px>=0 && px<n && py>=0 && py<m) 
            return true;
        return false;
    }
}
//My Solution: Dijistra. Queue stores the status of game(boxPos and manPos) when the first time find the box is at target then return push
class Solution{
	class Status{
		int[] manPos;
		int[] boxPos;
		int push = 0;
		public Status(int[] mp, int[] bp, int p){
            manPos = mp;
            boxPos = bp;
            push = p;
        }
        public int getID(){
            return getManID() * hash + getBoxID();
        }
        private int getManID(){
            return manPos[0] * col + manPos[1];
        }

        private int getBoxID(){
            return boxPos[0] * col + boxPos[1];
        }
    }
    int row;
    int col;
    int hash;
	public int minPushBox(char[][] grid){
		int[] boxPos = new int[2];
		int[] manPos = new int[2];
		int[] tarPos = new int[2];

		row = grid.length;
		col = grid[0].length;
        hash = row * col;
		for(int r = 0; r < row; ++r){
	        for(int c = 0; c < col; ++c){
	            if(grid[r][c] == 'T'){
	                tarPos[0] = r;
	                tarPos[1] = c;
                }else if(grid[r][c] == 'B'){
                    boxPos[0] = r;
                    boxPos[1] = c;
                }else if(grid[r][c] == 'S'){
                    manPos[0] = r;
                    manPos[1] = c;
                }
            }
        }
        Set<Integer> visited = new HashSet();
        PriorityQueue<Status> q = new PriorityQueue<Status>((a, b) -> (int)a.push - (int)b.push);
        q.offer(new Status(manPos, boxPos, 0));
        int[] dirs = {-1, 0, 1, 0, -1};
        while(!q.isEmpty()){
            Status curStatus = q.poll();
            if(curStatus.boxPos[0] == tarPos[0] && curStatus.boxPos[1] == tarPos[1]){
                return curStatus.push;
            }
            for(int i = 0; i < 4; ++i){
                int px = curStatus.manPos[0] + dirs[i];
                int py = curStatus.manPos[1] + dirs[i + 1];
                if( px < 0 || px == row || py < 0 || py == col || grid[px][py] == '#'){
                    continue;
                }
                int bx = curStatus.boxPos[0];
                int by = curStatus.boxPos[1];
                int push = curStatus.push;
                if(bx == px && by == py){
                    bx += dirs[i];
                    by += dirs[i + 1];
                    push += 1;
                }
                if(bx < 0 || by < 0 || bx == row || by == col || grid[bx][by] == '#'){
                    continue;
                }
                Status newStatus = new Status(new int[]{px, py}, new int[]{bx, by}, push);
                
                if(!visited.add(newStatus.getID())){
                    continue;
                }
                q.offer(newStatus); 
            }
        }
        return -1;
    } 
}
