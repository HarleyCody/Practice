___________________________________________________________________________Best Solution____________________________________________________________________________
// Key: label every node with maxHeight
// Traverse from node with lowest degree
// Use 2d Array to record parent
// Label parent with current height + 1, it will be the heighest height node.
// Only pick the node with hightest height

// just use array to replace hashmap and bfs
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        int[] edgeCounts = new int[n];
        
        for (int[] edge : edges) {
            edgeCounts[edge[0]]++;
            edgeCounts[edge[1]]++;
        }
        
        var queue = new ArrayDeque<Integer>();
        int[][] eMap = new int[n][];
        int[] visited = new int[n];
        
        for (int i = 0; i < n; i++) {
            eMap[i] = new int[edgeCounts[i]];
            if (edgeCounts[i] == 1) {
                visited[i] = 1;
                queue.addFirst(i);
            }
        }
        
        for (int[] edge : edges) {
            eMap[edge[0]][--edgeCounts[edge[0]]] = edge[1];
            eMap[edge[1]][--edgeCounts[edge[1]]] = edge[0];
        }
        
        int[] height = new int[n];
        
        while (!queue.isEmpty()) {

            for (int size = queue.size(); size > 0; size--) {
                
                int node = queue.removeFirst();
                
                for (int nextNode : eMap[node]) {
                    if (visited[nextNode] < eMap[nextNode].length - 1) {
                        height[nextNode] = height[node] + 1;
                        visited[nextNode]++;
                    }
                    if (visited[nextNode] == eMap[nextNode].length - 1) {
                        visited[nextNode]++;
                        queue.addLast(nextNode);
                    }
                }
                
            }
        }
                
        List<Integer> result = new ArrayList<>(2);
        
        for (int i = 0, max = -1; i < n; i++) {
            if (height[i] > max) {
                result.clear();
                max = height[i];
                result.add(i);
            } else if (height[i] == max) {
                result.add(i);
            }
        }
        
        return result;
    }
}
____________________________________________________________________________My Solution____________________________________________________________________________
// most naive way, reduce from node with degree 1(the outter layer)
class Solution {
    
    HashMap<Integer, HashSet<Integer>> revertIdx = new HashMap();
    int[] rank;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList();
        rank = new int[n];
        for(int i = 0; i < n; ++i){
            revertIdx.put(i, new HashSet());
        }
        for(int[] edge : edges){
            ++rank[edge[0]];
            ++rank[edge[1]];
            revertIdx.get(edge[0]).add(edge[1]);
            revertIdx.get(edge[1]).add(edge[0]);
        }
        HashSet<Integer> removeSet = new HashSet();
        while(revertIdx.size() > 2){
            for(int i = 0; i < n; ++i){
                if(rank[i] == 1 && revertIdx.containsKey(i)){
                    removeSet.add(i);
                }
            }
            for(int i : removeSet){
                update(i);
            }
        }
        return new ArrayList(revertIdx.keySet());
    }
    
    private void update(int num){
        if(!revertIdx.containsKey(num)){
            return;
        }
        
        for(int n : revertIdx.get(num)){
            --rank[n];
        }
        
        revertIdx.remove(num);
    }
}
