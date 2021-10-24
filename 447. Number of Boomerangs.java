//My Solution: choose one point to be mid and find the other two points
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap();
        for(int[] p1 : points){
            map.clear();
            for(int[] p2 : points){
                int d = getDist(p1, p2);
                int prev = map.getOrDefault(d, 0);
                ans += prev * 2;
                map.put(d, prev + 1);
            }
        }
        return ans;
    }
    private int getDist(int[] p1, int[] p2){
        int x = Math.abs(p1[0] - p2[0]);
        int y = Math.abs(p1[1] - p2[1]);
        return x * x + y * y;
    }
}
