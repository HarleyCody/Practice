class Solution {
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int [] visited = new int[len];
        int color = 1;
        for(int i = 0 ; i < graph.length; ++i){// incase of idependant node, paint all node
            if(visited[i] == 0 && !paint(graph, i, color, visited)){// previous node may not connect with current node so they may have same color(visited[i] == -color is not needed);
                return false;
            }
            // in case of previous is connected with next node, so change color.
            color = -color;
        }
        return true;
    }
    // paint node layer by layer with DFS 
    private boolean paint(int[][]graph, int pos, int color, int[] visited){
        if(visited[pos] == -color) return false;
        
        visited[pos] = color;
        for(int i = 0; i < graph[pos].length; ++i){
            //change color to paint next node in adjascent ndoe set
            // when adjascent has been painted with same color, or it has not been colored, but can be painted with different color node by node, paint cannot be done.
            // visited[graph[pos][i]] is necessary otherwise it will cause stackOverflow, as it recursion when next node has different color.
            // StackOverFlow, strict with conditions or add more base cases, otherwise it will never ends
            if(visited[graph[pos][i]] == color || (visited[graph[pos][i]] == 0 && !paint(graph, graph[pos][i], -color, visited))){
                return false;
            }
        }
        return true;
    }
}
