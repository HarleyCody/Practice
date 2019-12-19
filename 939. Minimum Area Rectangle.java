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
