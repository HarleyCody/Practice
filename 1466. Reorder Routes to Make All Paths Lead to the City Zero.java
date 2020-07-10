___________________________________________________________________________Best Solution O(n)________________________________________________________________________________
class Solution {
    // collect reversed road to its parent;
    // from always collect one more than to;
    // impossible from and to are both arrived due to single path
    // from arrives earlier means its to should be resversed as well so it collect node to + 1;
    // node to only record the number of reverse path after it away from 0
    public int minReorder(int n, int[][] roads) {
        int result = 0;
        int[] out = new int[n];
        boolean[] reached = new boolean[n];
        reached[0] = true;

        for (int[] road : roads) {
            if (reached[road[0]]) {
                reached[road[1]] = true;
                result += out[road[1]] + 1;
            } else if (reached[road[1]]) {
                reached[road[0]] = true;
                result += out[road[0]];
            } else {
                int sumOut = out[road[0]] + out[road[1]];
                out[road[0]] = sumOut + 1;
                out[road[1]] = sumOut;
            }
        }
        return result;
    }
}
_____________________________________________________________________________My Solution BFS________________________________________________________________________________
class Solution {
    // record every destination, road away => ans + 1
    // no matter in or out, q.offer(child)
    public int minReorder(int n, int[][] connections) {
        List<Integer>[] out = new ArrayList[n];
        List<Integer>[] in = new ArrayList[n];
        for(int[] con : connections){
            if(out[con[0]] == null){
                out[con[0]] = new ArrayList();
            }
            out[con[0]].add(con[1]);
            
            if(in[con[1]] == null){
                in[con[1]] = new ArrayList();
            }
            in[con[1]].add(con[0]);
        }
        
        int ans = 0;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Queue<Integer> q = new LinkedList();
        q.offer(0);
        while(!q.isEmpty()){
            int cur = q.poll();
            
            if(in[cur] != null){
                for(int i : in[cur]){
                    if(!visited[i]){
                        q.offer(i);
                        visited[i] = true;
                    }
                }
            }
            if(out[cur] != null){
                for(int i : out[cur]){
                    if(!visited[i]){
                        q.offer(i);
                        visited[i] = true;
                        ++ans;
                    }
                }
            }
        }
        
        return ans;
    }
}
