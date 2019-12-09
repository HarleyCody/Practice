________________________________________________________Best Solution__________________________________________________________
class Solution {
    public int maximumMinimumPath(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int lo = 1;
        int hi = Math.min(A[0][0], A[R - 1][C - 1]) + 1;
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        // binary search, set limit for every A[i][j]
        // range is 1 ~ Math.max(start, dest) + 1, mid would be(0 ~ high)
        // Math.max(star, dest) + 1: +1 cause start or dest would be the smallest in the path, so limit should be able to be Math.max(star, dest) when lo = mid = high; 
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            boolean[][] visited = new boolean[R][C];
            // limit is doable
            if (isValid(A, mid, visited, dirs, 0, 0, R, C)) {
                // try higher limit
                lo = mid + 1;
            } else {
                // limit is too high
                // try smaller limit
                hi = mid;
            }
        }
        return lo - 1;
    }
    // make sure every node in path >= limit and can reach to the dest   
    // bfs check out this binary search solution I found in the submissions
    private boolean isValid(int[][] A, int limit, boolean[][] marked, int[] dirs, int i, int j, int m, int n) {
        marked[i][j] = true;
        if (i == m - 1 && j == n - 1) {
            return true;
        }
        for (int k=0; k<4; ++k) {
            int ni = i + dirs[k];
            int nj = j + dirs[k + 1];
            if (ni >= 0 && nj >= 0 && ni < m && nj < n && !marked[ni][nj] && A[ni][nj] >= limit) {
                if (isValid(A, limit, marked, dirs, ni, nj, m, n)) {
                    return true;
                }
            }
        }
        return false;
    }
}
_________________________________________________________BFS Solution__________________________________________________________
// Dijkstra. BFS
class Solution {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maximumMinimumPath(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        
        if (R == 1 && C == 1) return A[0][0];
        
        // Descending order, assure next point is biggest choice in the next steps
        PriorityQueue<int[]> pq = new PriorityQueue<>(R * C, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return A[b[0]][b[1]] - A[a[0]][a[1]];
            } 
        });

        boolean[][] visited = new boolean[R][C];
        
        pq.offer(new int[]{0, 0});
        visited[0][0] = true;
        int min = Math.min(A[0][0], A[R-1][C-1]);
        while(!pq.isEmpty()){
            int[] p = pq.poll();
            int r = p[0];
            int c = p[1];
            min = Math.min(min, A[r][c]);
            for(int[] dir: dirs) {
                int i = r + dir[0];
                int j = c + dir[1];
                if (i < 0 || i >= R || j < 0 || j >= C || visited[i][j]) continue;
                if (i == R - 1 && j == C - 1) return min;
                pq.offer(new int[]{i, j});
                visited[i][j] = true;
            }
        }
        // should not get here
        return -1;
    }
}
