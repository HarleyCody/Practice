______________________________________________________Best Solution____________________________________________________________
class Solution {
// find other two piont directly if cur possible area is smaller
    int ans = Integer.MAX_VALUE;
    public int minAreaRect(int[][] points) {
        if(points.length < 4) return 0;
        int len = points.length;
        
        for(int i = 0; i < len; ++i){
            for(int j = 0; j < len; ++j){
                if(points[i][0] == points[j][0] || points[i][1] == points[j][1]) continue;
                
                int area = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[j][1] - points[i][1]);
                // key for not TLE, do not excute next O(n) operation to find
                if(area > ans) continue;
                boolean p3 = false;
                for(int[] p : points){
                    if(p[0] == points[i][0] && p[1] == points[j][1]){
                        p3 = true;
                        break;
                    }
                }
                boolean p4 = false;
                for(int[] p : points){
                    if(p[0] == points[j][0] && p[1] == points[i][1]){
                        p4 = true;
                        break;
                    }
                }
                if(p3 && p4){
                    ans = area;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
______________________________My Solution(Improved, use hashset instead of hashmap)____________________________________________
class Solution {
    HashSet<Integer> recorder = new HashSet();
    int ans = Integer.MAX_VALUE;
    public int minAreaRect(int[][] points) {
        if(points.length < 4) return 0;
        int len = points.length;
        
        int col = 40000, key;
        for(int i = 0; i < len; ++i){
            key = points[i][0] * col + points[i][1];
            recorder.add(key);
        }
        for(int i = 0; i < len; ++i){
            for(int j = 0; j < len; ++j){
                if(points[i][0] == points[j][0] || points[i][1] == points[j][1]) continue;
                int p1 = points[i][0] * col + points[j][1];
                int p2 = points[j][0] * col + points[i][1];
                if(recorder.contains(p1) && recorder.contains(p2)){
                    ans = Math.min(ans, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[j][1] - points[i][1]));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
_______________________________________________________My Solution____________________________________________________________
// choose diagonal find other two points, exist calculate area otherwise choose other diagonals
class Solution {
    HashMap<Integer, List<int[]>> recorder = new HashMap();
    int ans = Integer.MAX_VALUE;
    public int minAreaRect(int[][] points) {
        if(points.length < 4) return 0;
        int len = points.length;
        int col = 40000;
        for(int i = 0; i < len; ++i){
            int[] cur = points[i];
            int key = cur[0] * col + cur[1];
            List<int[]> diagonals = new ArrayList();
            for(int[] p : points){
                if(p[0] == cur[0] || p[1] == cur[1]) continue;
                diagonals.add(p);
            }
            recorder.put(key, diagonals);
        }
        for(Map.Entry e : recorder.entrySet()){
            int key = (int)e.getKey();
            int x = key / col;
            int y = key % col;
            List<int[]> diagonals = (List)e.getValue();
            for(int[] d : diagonals){
                int p1 = x * col + d[1];
                int p2 = d[0] * col + y;
                if(recorder.containsKey(p1) && recorder.containsKey(p2)){
                    ans = Math.min(ans, Math.abs(d[1] - y) * Math.abs(d[0] - x));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
