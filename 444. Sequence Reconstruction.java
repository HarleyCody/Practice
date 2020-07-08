class Solution {
    // record indice and check every prev next pair is in seqs
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // 其实不需要那么复杂，要想让org是一个唯一的，那么org里面的每一对相邻的数字，都必须在seqs里面出现！可以用反证法证明
        int[] pos = new int[org.length + 1];
        for (int i = 0; i < org.length; i++) {
            pos[org[i]] = i;
        }
        
        int count = org.length;   // this is to make sure that all numbers will be visited
        boolean[] visited = new boolean[count + 1];
        
        for (List<Integer> seq: seqs) {
            int prev = -1;
            for (int num : seq) {
                if (num < 0 || num > org.length || pos[num] <= prev) return false;
                if (!visited[num] && pos[num] == prev + 1) {
                    visited[num] = true;
                    count --;
                }
                prev = pos[num];
            }
        }
        
        return count == 0;
        
    }
    
    // similar to topological sort, but with BFS
    // start with 0 indgree 
    public boolean sequenceReconstruction2(int[] org, List<List<Integer>> seqs) {
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer>  indegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0 ; i < seq.size(); i++) {
                int cur = seq.get(i);
                graph.putIfAbsent(cur, new HashSet<>());
                indegree.putIfAbsent(cur, 0);
                if (i > 0) {
                    int last = seq.get(i-1);
                    if (graph.get(last).add(cur)) {
                        indegree.put(cur, indegree.get(cur) + 1);
                    }
                }
            }
        }
        
        if (indegree.size() != org.length) return false;
        
        Queue<Integer> queue = new LinkedList<>();
        for (int key: indegree.keySet()) {
            if (indegree.get(key) == 0) queue.add(key);
        }
        
        int i = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) return false;
            int cur = queue.poll();

            if (org[i++] != cur) return false;
            for (int next: graph.get(cur)) {
                indegree.put(next, indegree.get(next) - 1);
                // head of next
                if (indegree.get(next) == 0) queue.offer(next);
            }
        }
        
        return i == org.length;
        
    }
}
