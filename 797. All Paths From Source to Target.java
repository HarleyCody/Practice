__________________________________________________________________________________My Best Solution________________________________________________________________________
// improed List<LinkedList<Integer>>
// use LinkedList for path as offer and removeLast is O(1)
// mem is trivial as the graph is acyclic and directed
class Solution {
    List<List<Integer>> ans = new ArrayList();
    int N;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        N = graph.length;
        findPath(graph, 0, new LinkedList());
        return ans;
    }
    
    private void findPath(int[][] graph, int curPos, LinkedList<Integer> curPath){
        if(curPos == N - 1){
            curPath.offer(N - 1);
            ans.add(new LinkedList(curPath));
            curPath.removeLast();
            return;
        }
        curPath.offer(curPos);
        for(int i : graph[curPos]){
            findPath(graph, i, curPath);
        }
        curPath.removeLast();
    }
}
____________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    List<List<Integer>>[] mem;
    int N;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        N = graph.length;
        mem = new ArrayList[N];
        return findPath(graph, 0);
    }
    
    private List<List<Integer>> findPath(int[][] graph, int curPos){
        if(mem[curPos] != null){
            return mem[curPos];
        }
        
        List<List<Integer>> ans = new ArrayList();
        List<Integer> curPath = new ArrayList();
        if(curPos == N - 1){
            curPath.add(0, N - 1);
            ans.add(curPath);
            return ans;
        }
        for(int i : graph[curPos]){
            List<List<Integer>> next = findPath(graph, i);
            if(next.isEmpty()){
                continue;
            }
            for(List<Integer> l : next){
                curPath = new ArrayList(l);
                curPath.add(0, curPos);
                ans.add(curPath);
            }
        }
        
        mem[curPos] = ans;
        return ans;
    }
}
