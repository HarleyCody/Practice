//Best Solution: Check if the second third and forth point is in the map and get count by weight
//Get the diagnal first and check left two coners
class DetectSquares {
    
    Map<Integer, Integer>[] count = new Map[1001];
    
    DetectSquares() {
        for (int x = 0; x <= 1000; x ++) {
            count[x] = new HashMap<>();
        }
    }
    
    public void add(int[] point) {
        int x = point[0], y = point[1];
        count[x].put(y, count[x].getOrDefault(y, 0) + 1);
    }
    
    public int count(int[] point) {
        int x = point[0], y = point[1], ans = 0;
        for (int y1 : count[x].keySet()) {
            if (y != y1) {
                int r = Math.abs(y - y1);
                for (int x1 : new int[] { x + r, x - r }) {
                    if (0 <= x1 && x1 <= 1000) {
                        ans += count[x].get(y1) * count[x1].getOrDefault(y1, 0) 
                               * count[x1].getOrDefault(y, 0);
                    }
                }
            }
        }
        return ans;
    }
}
//My Solution: find by distance and x or y recursively to determine the square
class DetectSquares {
    HashMap<Integer, Map<Integer, Integer>> xRecorder;
    HashMap<Integer, Map<Integer, Integer>> yRecorder;
    public DetectSquares() {
        xRecorder = new HashMap<Integer, Map<Integer, Integer>>();
        yRecorder = new HashMap<Integer, Map<Integer, Integer>>();
    }
    
    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        int freq = 0;
        xRecorder.putIfAbsent(x, new HashMap<Integer, Integer>());
        freq = xRecorder.get(x).getOrDefault(y, 0);
        xRecorder.get(x).put(y, freq + 1);
        yRecorder.putIfAbsent(y, new HashMap<Integer, Integer>());
        freq = yRecorder.get(y).getOrDefault(x, 0);
        yRecorder.get(y).put(x, freq + 1);
    }
    
    public int count(int[] point) {
        if(!xRecorder.containsKey(point[0]) || !yRecorder.containsKey(point[1])) return 0;
            
        int x = point[0];
        int ans = 0;
        for(int y : xRecorder.get(x).keySet()){
            if(y == point[1]) continue;
            int d = Math.abs(y - point[1]);
            ans += count(point[0], point[1], x, y, d, 1, xRecorder.get(x).get(y));
        }
        return ans;
    }
    
    private int count(int ox, int oy, int cx, int cy, int d, int steps, int cnt){

        int ans = 0;
        int next = 0;
        ++steps;
        if(steps == 4){
            next = cx + d;
            ans += next == ox && cy == oy ? cnt : 0;
            
            next = cx - d;
            ans += next == ox && cy == oy ? cnt : 0;
            
        }else if(steps == 2){
            Map<Integer, Integer> nextMap = yRecorder.get(cy);
            if(nextMap == null) return ans;
            
            next = cx + d;
            if(nextMap.containsKey(next)){
                ans += count(ox, oy, next, cy, d, steps, nextMap.get(next) * cnt);
            }
            next = cx - d;
            if(nextMap.containsKey(next)){
                ans += count(ox, oy, next, cy, d, steps, nextMap.get(next) * cnt);
            }
        }else{
            Map<Integer, Integer> nextMap = xRecorder.get(cx);
            if(nextMap == null) return ans;
            next = cy + d;
            if(nextMap.containsKey(next)){
                ans += count(ox, oy, cx, next, d, steps, nextMap.get(next) * cnt);
            }
            next = cy - d;
            if(nextMap.containsKey(next)){
                ans += count(ox, oy, cx, next, d, steps, nextMap.get(next) * cnt);
            }
        }
        
        return ans;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
