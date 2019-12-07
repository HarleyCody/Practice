_______________________________________________________Best Solution ________________________________________________________
class Solution {
    private final int[][] DIRS = {{1,0}, {0,1}, {-1, 0}, {0, -1}, {1,1},{1,-1},{-1,1}, {-1,-1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) {
            return -1;
        }
        List<int[]> l = new ArrayList<>();
        l.add(new int[]{0, 0});
        return helper(grid, l, 1);
    }

    public int helper(int[][] g, List<int[]> l, int d) {
        if(l.size() == 0) {
            return -1;
        }

        int N = g.length;

        List<int[]> nl = new ArrayList<>();
        for(int i=0; i<l.size(); i++) {
            int m = l.get(i)[0];
            int n = l.get(i)[1];

            if(m == N-1 && n == N-1) {
                return d;
            }

            if(m - 1 >= 0 && n-1 >= 0 && g[m-1][n-1] == 0) {
                g[m-1][n-1] = 1;
                nl.add(new int[]{m-1, n-1});
            }
            if(m-1 >= 0 && g[m-1][n] == 0) {
                g[m-1][n] = 1;
                nl.add(new int[]{m-1, n});
            }
            if(m-1 >= 0 && n+1 < N && g[m-1][n+1] == 0) {
                g[m-1][n+1] = 1;
                nl.add(new int[]{m-1, n+1});
            }
            if(n+1 < N && g[m][n+1] == 0) {
                g[m][n+1] = 1;
                nl.add(new int[]{m, n+1});
            }
            if(m+1 < N && n+1 < N && g[m+1][n+1] == 0) {
                g[m+1][n+1] = 1;
                nl.add(new int[]{m+1, n+1});
            }
            if(m+1 < N && g[m+1][n] == 0) {
                g[m+1][n] = 1;
                nl.add(new int[]{m+1, n});
            } 
            if(m+1 < N && n-1 >= 0 && g[m+1][n-1] == 0) {
                g[m+1][n-1] = 1;
                nl.add(new int[]{m+1, n-1});
            }
            if(n-1 >= 0 && g[m][n-1] == 0) {
                g[m][n-1] = 1;
                nl.add(new int[]{m, n-1});
            }
        }
        return helper(g, nl, d+1);
    }
}
________________________________________________________BFS _________________________________________________________________
class Solution {
    int[][] dirs = {{-1, -1},{-1, 0},{-1, 1},{0, 1}, {1, 1},{1,0},{1, -1},{0, -1}};
    Queue<int[]> cur = new LinkedList();
    boolean[][] visited;
    public int shortestPathBinaryMatrix(int[][] grid) {
        int len = grid.length;
        
        if(grid.length == 0 || grid[0].length == 0 || (grid[0][0] ^ grid[len - 1][len - 1]) != 0) return -1;
        
        visited = new boolean[len][len];
        int ans = 0;
        cur.offer(new int[]{0,0});
        visited[0][0] = true;
        while(!cur.isEmpty()){
            int size = cur.size();
            // set for levels
            for(int times = 0; times < size; times++){
                int[] curIdx = cur.poll();
                if(curIdx[0] == len - 1 && curIdx[1] == len - 1){
                    return ans + 1;
                }
                // offer next level;
                for(int i = 0; i < 8; ++i){
                    int x = curIdx[0]  + dirs[i][0];
                    int y = curIdx[1] + dirs[i][1];
                    if(x < 0 || y < 0 || x == len || y == len || grid[x][y] == 1 || visited[x][y]) continue;
                    cur.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            // increase level;
            ++ans;
        }
        return -1;
    }
}
