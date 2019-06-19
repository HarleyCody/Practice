class Solution {
   public boolean canFinish(int numCourses, int[][] prerequisites) {
       // graph, pre of current courses, current courses
        List<List<Integer>> graph = new ArrayList();
        
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(graph, visit, i))
                return false;
        }
        
        return true;
    }
    
    private boolean canFinishDFS(List<List<Integer>> graph, int[] visit, int start) {
        if (visit[start] == -1)// circle [[1,0],[0,1],[1,2]] still false
            return false;
        if (visit[start] == 1) // this state is used for accelerate dfs, study next course in last layer
            return true;
        
        visit[start] = -1; // state: is visiting, after studing this course, can study any course in list.
        for (int end: graph.get(start)) {
            if (!canFinishDFS(graph, visit, end))
                return false;
        }
        
        visit[start] = 1; // state: visited
        return true;
    }
}
