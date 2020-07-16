________________________________________________________________________________My Solution_____________________________________________________________________
class Solution {
    // count num of crevice between bricks at len i
    // choose i with most crevice to draw the line
    // ans = walls.size() - # of most crevice
    public int leastBricks(List<List<Integer>> walls) {
        HashMap<Integer, Integer> recorder = new HashMap();
        int max = 0;
        for(List<Integer> wall : walls){
            int len = 0;
            int size = wall.size();
            for(int s = 0; s < size - 1; ++s){
                len += wall.get(s);
                int num = recorder.getOrDefault(len, 0);
                recorder.put(len, num + 1);
                max = Math.max(num + 1, max);
            }
        }
        return walls.size() - max;
    }
}
