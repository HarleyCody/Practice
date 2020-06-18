____________________________________________________________Best Solution____________________________________________________________
class Solution {
    // instead of using pq, create a comparable class
    private int water = 0;    
    private boolean[][] visited;
    // faster speed, cause some node has been visited but still in pq, so use count to record times
    private int visitedCnt = 0;// 已访问的次数
    private static class Wall implements Comparable<Wall> {
        int row;
        int col;
        int value;
        Wall(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
        @Override
        public int compareTo(Wall wall) {
            return value - wall.value;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
            return 0;
        }
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        PriorityQueue<Wall> pq = new PriorityQueue<Wall>();
        visited = new boolean[rows][cols];

        for (int i = 0; i < cols; i++) {
            Wall wall1 = new Wall(0, i, heightMap[0][i]);
            Wall wall2 = new Wall(rows - 1, i, heightMap[rows - 1][i]);
            pq.offer(wall1);
            pq.offer(wall2);
            visited[0][i] = true;
            visited[rows - 1][i] = true;
            visitedCnt += 2;
        }

        for (int i = 1; i < rows - 1; i++) {
            Wall wall1 = new Wall(i, 0, heightMap[i][0]);
            Wall wall2 = new Wall(i, cols - 1, heightMap[i][cols - 1]);
            pq.offer(wall1);
            pq.offer(wall2);
            visited[i][0] = true;
            visited[i][cols - 1] = true;
            visitedCnt += 2;
        }

        while (pq.size() > 0 && visitedCnt < rows * cols) {
            Wall wall = pq.poll();
            fill(wall.row - 1, wall.col, wall.value, pq, heightMap);
            fill(wall.row, wall.col - 1, wall.value, pq, heightMap);
            fill(wall.row + 1, wall.col, wall.value, pq, heightMap);
            fill(wall.row, wall.col + 1, wall.value, pq, heightMap);
        }
        return water;
    }


    public void fill(int row, int col, int min, PriorityQueue<Wall> pq, int[][] heightMap) {
        if (row < 0 || col < 0) {
            return;
        } else if (row >= heightMap.length || col >= heightMap[0].length) {
            return;
        } else if (visited[row][col]) {
            return;
        } else if (heightMap[row][col] >= min) {
            Wall wall1 = new Wall(row, col, heightMap[row][col]);
            pq.offer(wall1);
            visited[row][col] = true;
            visitedCnt++;
        } else {
            water += min - heightMap[row][col];
            visited[row][col] = true;
            visitedCnt++;
            fill(row - 1, col, min, pq, heightMap);
            fill(row, col - 1, min, pq, heightMap);
            fill(row + 1, col, min, pq, heightMap);
            fill(row, col + 1, min, pq, heightMap);
        }
    }
    
    
}
____________________________________________________________General Solution____________________________________________________________
class Solution {
    // start from lowest wall, collect water if find lower cell, otherwise increase wall and continue finding
    int[] dirs = {1, 0, -1, 0, 1};
    public int trapRainWater(int[][] heightMap) {
        int R = heightMap.length;
        if(R == 0){
            return 0;
        }
        int C = heightMap[0].length;
        boolean[][] visited = new boolean[R][C];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        
        for(int i = 0; i < C; ++i){
            visited[0][i] = visited[R - 1][i] = true;
            pq.offer(new int[]{0, i, heightMap[0][i]});
            pq.offer(new int[]{R - 1, i, heightMap[R - 1][i]});
        }
        
        for(int i = 0; i < R; ++i){
            visited[i][0] = visited[i][C - 1] = true;
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, C - 1, heightMap[i][C - 1]});
        }
        int ans = 0;
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            
            for(int i = 0; i < 4; ++i){
                int nx = node[0] + dirs[i];
                int ny = node[1] + dirs[i + 1];
                
                if(nx == R || nx == -1 || ny == -1 || ny == C || visited[nx][ny]){
                    continue;
                }
                ans += Math.max(node[2] - heightMap[nx][ny], 0);
                visited[nx][ny] = true;
                pq.offer(new int[]{nx, ny, Math.max(heightMap[nx][ny], node[2])});
            }
        }
        return ans;
    }
}
