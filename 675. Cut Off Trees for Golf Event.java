______________________________________________________Best Solution(pq + bfs)__________________________________________________
class Solution {
// mainly different is use Deque to bfs
// calculate abs distance between cur to tar, if 0 return dis otherwise add adjs
// if abs between adj to tar is less than cur to tar, put to head(deque push) otherwise if it is valid put to tail
// 
    static final int[][] offsets = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        public int cutOffTree(List<List<Integer>> forest) {
        int rows = forest.size();
        int columns = forest.get(0).size();
        int[][] matrix = new int[rows][columns];

        int[][] rcAndVal = new int[rows*columns][];

        for (int i = 0; i < rows; i++) {
            List<Integer> row = forest.get(i);
            for (int j=0; j<columns; j++) {
                matrix[i][j] = row.get(j);
                int rcAndValIndex = i*columns + j;
                rcAndVal[rcAndValIndex] = new int[] {i, j, row.get(j)};
            }
        }

        Arrays.sort(rcAndVal, (a,b) -> a[VAL] - b[VAL]);
        
        int steps = 0;
        
        int currentR = 0;
        int currentC = 0;
        final int VAL = 2;
        final int ROW = 0;
        final int COL = 1;
        for (int i = 0; i < rcAndVal.length; i++) {
            int[] rcv = rcAndVal[i];
            int row = rcv[ROW];
            int col = rcv[COL];
            if (rcv[VAL] <= 1)  
                continue; 
            int path = findShortestPath(currentR, currentC, row, col, matrix);
            if (path < 0) 
                return -1;
            steps += path;
            currentR = row;
            currentC = col;
        }
        
        return steps;
    }
    
    public int findShortestPath(int fromRow, int fromCol, int toRow, int toCol, int[][] matrix) { 
        int steps = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Deque<int[]> visit = new ArrayDeque<>(); 
        
        visit.offer(new int[] {fromRow, fromCol, 0});
        
        while (visit.size() != 0) {
            int[] node = visit.poll();
            int row = node[0];
            int col = node[1];
            
            if (!visited[row][col]) {
                visited[row][col] = true;
                int distance = node[2];
                int nodeDistanceToTarget = Math.abs(row - toRow) + Math.abs(col-toCol);
                if (nodeDistanceToTarget == 0) { return distance; }
                else {
                    for (int[] offset : offsets) {
                        int r = row + offset[0];
                        int c = col + offset[1];
                        int adjDistanceToTarget = Math.abs(r - toRow) + Math.abs(c-toCol);
                        int[] adjNode = new int[] {r, c, distance + 1};
                        // must be valid node as adj < nodeDis, adj must be in maps
                        if (adjDistanceToTarget < nodeDistanceToTarget && matrix[r][c] != 0) 
                            visit.push(adjNode);
                        else if (r>=0 && r < matrix.length && c>=0 && c < matrix[0].length && matrix[r][c] != 0) 
                            visit.offer(adjNode);
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
