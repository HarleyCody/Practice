//My Solution: Only add new status when at nx and ny and distance is shorter than ever distance
class Solution {
    class Status{
        int x;
        int y;
        int dis;
        String path;
        
        public Status(int x, int y, int dis, String path){
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.path = path;
        }
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int row = maze.length;
        int col = maze[0].length;
        
        int[][] visited = new int[row][col];
        for(int[] visit : visited){
            Arrays.fill(visit, Integer.MAX_VALUE);
        }
        visited[ball[0]][ball[1]] = 0;
        
        PriorityQueue<Status> q = new PriorityQueue<Status>((a, b) -> a.dis == b.dis ? a.path.compareTo(b.path) : a.dis - b.dis);
        q.add(new Status(ball[0], ball[1], 0, ""));
        while(!q.isEmpty()){
            Status curStatus = q.poll();
            if(curStatus.x == hole[0] && curStatus.y == hole[1]){
                return curStatus.path;
            }
            int nx = curStatus.x;
            int ny = curStatus.y;
            int dis = curStatus.dis;
            //up
            while(nx >= 0 && maze[nx][ny] == 0){
                --nx;
                ++dis;
                if(nx + 1 == hole[0] && ny == hole[1]){
                    break;
                }
            }
            ++nx;
            --dis;
            if(dis <= visited[nx][ny] && nx != curStatus.x){
                visited[nx][ny] = dis;
                q.add(new Status(nx, ny, dis, curStatus.path + "u"));
            }
            nx = curStatus.x;
            dis = curStatus.dis;
            
            //down
            while(nx < row && maze[nx][ny] == 0){
                ++nx;
                ++dis;
                if(nx - 1 == hole[0] && ny == hole[1]){
                    break;
                }
            }
            --nx;
            --dis;
            if(dis <= visited[nx][ny] && nx != curStatus.x){
                visited[nx][ny] = dis;
                q.add(new Status(nx, ny, dis, curStatus.path + "d"));
            }
            nx = curStatus.x;
            dis = curStatus.dis;
            
            //left
            while(ny >= 0 && maze[nx][ny] == 0){
                --ny;
                ++dis;
                if(nx == hole[0] && ny + 1 == hole[1]){
                    break;
                }
            }
            ++ny;
            --dis;
            if(dis <= visited[nx][ny] && ny != curStatus.y){
                visited[nx][ny] = dis;
                q.add(new Status(nx, ny, dis, curStatus.path + "l"));
            }
            ny = curStatus.y;
            dis = curStatus.dis;
            
            //right
            while(ny < col && maze[nx][ny] == 0){
                ++ny;
                ++dis;
                if(nx == hole[0] && ny - 1 == hole[1]){
                    break;
                }
            }
            --ny;
            --dis;
            if(dis <= visited[nx][ny] && ny != curStatus.y){
                visited[nx][ny] = dis;
                q.add(new Status(nx, ny, dis, curStatus.path + "r"));
            }
        }
        
        return "impossible";
    }
}
