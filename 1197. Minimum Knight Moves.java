___________________________________________________________Best Solution(BFS + HashMap)__________________________________________________________________
https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)
class Solution {
    // patterns
    private int[][] localRegion = {
        {0, 3, 2},
        {3, 2, 1},
        {2, 1, 4}
    };
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        if (x <= 2 && y <= 2)
            return localRegion[x][y];

        int groupId;
        // sections a divide by a slope with y = 0.5 x;
        if ((x - 3) >= (y - 3) * 2)
            // below slope
            groupId = (x - 1) / 2 + 1;
        else
            // above slope
            groupId = (x + y - 2) / 3 + 1;

        return groupId + ((x + y + groupId) % 2);
    }
}

____________________________________________________________My Solution(BFS + HashMap)__________________________________________________________________
class Solution {
    int[] dirs = new int[]{-2, -1, 2, -1, -2, 1, 2, 1, -2};
    HashMap<Integer, HashSet<Integer>> visited = new HashMap();
    public int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) return 0;
        Queue<int[]> recorder = new LinkedList();
        recorder.offer(new int[]{0, 0});
        int ans = 0;
        while(!recorder.isEmpty()){
            int size = recorder.size();
            for(int times = 0; times < size; ++times){
                int[] cur = recorder.poll();
                
                if(visited.get(cur[0]) != null && visited.get(cur[0]).contains(cur[1])) continue;
                HashSet<Integer> med = visited.getOrDefault(cur[0], new HashSet());
                med.add(cur[1]);
                visited.put(cur[0], med);
                for(int i = 0; i < 8; ++i){
                    int nx = cur[0] + dirs[i];
                    int ny = cur[1] + dirs[i + 1];
                    if(nx == x && ny == y)return ans + 1;
                    recorder.offer(new int[]{nx, ny});
                }
            }
            ++ans;
        }
        return -1;
    }
}
