//Best Solution: BFS with bitmask, 1 means visited 0 means not visited
class Solution {
    
    public int shortestPathLength(int[][] graph) {
        int target = (1 << graph.length) - 1;
        // q consists of pairs [n, state] where n represents the node 
        // and state is the bitmask representing all the visited nodes
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < graph.length; ++i) {
            q.offer(new int[]{i, 1 << i});
        }
        int steps = 0;
        boolean[][] visited = new boolean[graph.length][target+1];
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                var curr = q.poll();
                int currNode = curr[0];
                int currState = curr[1];
                if (currState == target) return steps;
                for (int n : graph[currNode]) {
                    int newState = currState | 1 << n;
                    if (visited[n][newState]) continue;
                    visited[n][newState] = true;
                    q.offer(new int[]{n, newState});
                }
            }
            ++steps;
        } 
        return -1;
    }
}
