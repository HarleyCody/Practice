//Best Solution: Use binary serach to determine the boundary(As there is only one black region), TC Math.max(log(height), log(width))
class Solution {
    
    boolean hasBlackPixelRow(char[][] image, int j) {
        int n = image.length;
        for (int i = 0; i < n; i++) {
            if (image[i][j] == '1') {
                return true;
            }
        }
        return false;
    }
    
    boolean hasBlackPixelColumn(char[][] image, int i) {
        int m = image[0].length;
        for (int j = 0; j < m; j++) {
            if (image[i][j] == '1') {
                return true;
            }
        }
        return false;
    }
    
    public int minArea(char[][] image, int x, int y) {
        int n = image.length;
        int m = image[0].length;
        
        
        int left_column;
        int right_column;
        
        int up_row;
        int down_row;
        
        //lower column
        int lo = 0;
        int hi = y;
        
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            
            if (hasBlackPixelRow(image, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }
        
        left_column = lo;
        
        //higher column
        lo = y;
        hi = m - 1;
        
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (lo == mid) mid++;
            
            if (hasBlackPixelRow(image, mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        
        right_column = lo;
        
        //lower row
        lo = 0;
        hi = x;
        
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            
            if (hasBlackPixelColumn(image, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }
        
        up_row = lo;
        
        //higher column
        lo = x;
        hi = n - 1;
        
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (lo == mid) mid++;
            
            if (hasBlackPixelColumn(image, mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        
        down_row = lo;
           
        return (right_column - left_column + 1) * (down_row - up_row + 1);
    }
}
//My better Solution: DFS find boundary and calculate the area by (right - left + 1) * (bot - top + 1);
class Solution {
    int left;
    int right;
    int top;
    int bot;
    int row;
    int col;
    public int minArea(char[][] image, int x, int y) {
        left = y;
        right = y;
        top = x;
        bot = x;
        findBoundary(image, x, y);
        
        return (right - left + 1) * (bot - top + 1);
    }
    
    private void findBoundary(char[][] image, int x, int y){
        image[x][y] = '0';
        left = Math.min(left, y);
        right = Math.max(right, y);
        top = Math.min(top, x);
        bot = Math.max(bot, x);
        if(x > 0 && image[x - 1][y] == '1'){
            findBoundary(image, x - 1, y);
        }
        if(x < image.length - 1 && image[x + 1][y] == '1'){
            findBoundary(image, x + 1, y);
        }
        if(y > 0 && image[x][y - 1] == '1'){
            findBoundary(image, x, y - 1);
        }
        if(y < image[0].length - 1 && image[x][y + 1] == '1'){
            findBoundary(image, x, y + 1);
        }
    }
}
//My Solution: BFS to detect all connected black pixel, get boundary left right and top bot, calculate the area
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int row = image.length;
        int col = image[0].length;
        LinkedList<int[]> q = new LinkedList();
        q.offer(new int[]{x, y});
        image[x][y] = '0';
        
        int left = col - 1;
        int right = 0;
        int top = row - 1;
        int bot = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            left = Math.min(cur[1], left);
            right = Math.max(cur[1], right);
            top = Math.min(cur[0], top);
            bot = Math.max(cur[0], bot);
            
            for(int i = 0; i < 4; ++i){
                int nx = cur[0] + dirs[i];
                int ny = cur[1] + dirs[i + 1];
                
                if(nx < 0 || nx == row || ny < 0 || ny == col || image[nx][ny] != '1') continue;
                q.offer(new int[]{nx, ny});
                image[nx][ny] = '0';
            }
        }
        
        return (right - left + 1) * (bot - top + 1);
    }
}
