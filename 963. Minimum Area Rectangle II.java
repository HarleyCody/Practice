___________________________________________________________Best Solution________________________________________________________
class Solution {
// choose two side determine forth point
// rectangle is assure by the angle formed by two side with line x = p1[0], their production of tan value should be 1 
    public double minAreaFreeRect(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for (int[] point : points)
            set.add(point[0] * 40001 + point[1]);
        double res = Double.POSITIVE_INFINITY;
        int n = points.length;
        for (int i = 0; i < n - 2; i++) {
            int[] p1 = points[i];
            for (int j = i + 1; j < n - 1; j++) {
                int[] p2 = points[j];
                int dx1 = p2[0] - p1[0];
                int dy1 = p2[1] - p1[1];
                // choose a side
                if (dx1 == 0 && dy1 == 0)continue;
                
                for (int k = j + 1; k < n; k++) {
                    int[] p3 = points[k];
                    int dx2 = p3[0] - p1[0];
                    int dy2 = p3[1] - p1[1];
                    // p1 is middle side p1p2 and side p1p3 find point 4;
                    // rectangle: dy1/dx1 == dx2/dy2
                    // |dx1| * |dx2| == |dy2| * |dy1| == as one point below and one point above. so dy1 or dy2 might be negative. use -dy1 or - dy2;
                    // dx1 * dx2 = - dy1 * dy2
                    if (dx1 * dx2 + dy1 * dy2 != 0)continue;
                    
                    // when == 0 forth can be determined
                    int x = p2[0] + dx2;
                    int y = p2[1] + dy2;
                    
                    double area = Math.sqrt(dx1 * dx1 + dy1 * dy1) * Math.sqrt(dx2 * dx2 + dy2 * dy2);
                    if (area < res && set.contains(x * 40001 + y)) {
                            res = area;
                    }
                }
            }
        }
        return res == Double.POSITIVE_INFINITY ? 0 : res;
    }
}
____________________________________________________________My Solution________________________________________________________
class Solution {
    // find diagonal determine thrid by Pythagoras theorem（勾股）then calculate forth by central rotate)
    public double minAreaFreeRect(int[][] points) {
        double ans = Double.MAX_VALUE;
        int len = points.length;
        HashSet<Integer> recorder = new HashSet();
        for(int[]p : points){
            recorder.add(p[0] * 40000 + p[1]);
        }
        for(int i = 0; i < len; ++i){
            for(int j = i + 1; j < len; ++j){
                double diagonal = distance(points[i], points[j]);
                double dis1 = diagonal, dis2 = diagonal;
                for(int k = 0; k < len; ++k){
                    if(k == i || k == j)continue;
                    dis1 = distance(points[k],points[i]);
                    dis2 = distance(points[k],points[j]);
                    if(dis1 + dis2 == diagonal && Math.sqrt(dis1) * Math.sqrt(dis2) < ans){
                        // forth central rotate. forthX = j[x] + (-(newI - oldI))
                        // newI = thirdPoint, oldI = firstPoint
                        int x = points[j][0] + points[i][0] - points[k][0];
                        int y = points[j][1] + points[i][1] - points[k][1];
                      
                        if(recorder.contains(x * 40000 + y)){
                            ans = Math.min(ans, Math.sqrt(dis1) * Math.sqrt(dis2));
                        }
                    }
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0 : ans;
    }
    private double distance(int[] p1, int[] p2){
        int x = Math.abs(p1[0] - p2[0]);
        int y = Math.abs(p1[1] - p2[1]);
        return x * x + y * y; 
    }
}
